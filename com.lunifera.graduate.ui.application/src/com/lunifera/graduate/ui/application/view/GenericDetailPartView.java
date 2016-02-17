package com.lunifera.graduate.ui.application.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.lunifera.runtime.web.vaadin.common.resource.IResourceProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lunifera.graduate.ui.application.annotations.Delete;
import com.lunifera.graduate.ui.application.annotations.NewEntity;
import com.lunifera.graduate.ui.application.annotations.Refresh;
import com.lunifera.graduate.ui.application.view.api.IConstants;
import com.lunifera.graduate.ui.fields.OSGiBeanFieldGroup;
import com.lunifera.graduate.ui.forms.ComponentFactory;
import com.lunifera.graduate.ui.util.Util;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

public class GenericDetailPartView implements EventHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GenericDetailPartView.class);

	@Inject
	private IEclipseContext eclipseContext;
	@Inject
	private IEventBroker eventBroker;
	@Inject
	private MPart mPart;
	@Inject
	private VerticalLayout parentLayout;
	@Inject
	private org.eclipse.e4.core.services.events.IEventBroker e4EventBroker;
	@Inject
	private IResourceProvider resourceProvider;
	@Inject
	private EPartService partService;
	@Inject
	@Optional
	private EntityManager em;

	private Class<?> entityClass;

	private String eventTopic;

	private OSGiBeanFieldGroup<?> fieldGroup;

	private String detailViewClassName;

	private JPAContainerItem<?> item;
	private Object entity;

	public GenericDetailPartView() {
	}

	@PostConstruct
	public void setup() {
		initialize();

		parentLayout.setMargin(true);
		Design.setComponentFactory(new ComponentFactory());

		Component view = null;
		try {
			Class<?> detailContentClass = getClass().getClassLoader()
					.loadClass(detailViewClassName);
			view = (Component) detailContentClass.newInstance();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e1) {
			e1.printStackTrace();
		}
		view.setSizeFull();
		parentLayout.addComponent(view);
		parentLayout.setExpandRatio(view, 1.0f);

		try {
			Object entity = entityClass.newInstance();
			fieldGroup = OSGiBeanFieldGroup.bindFieldsBuffered(entity, view);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	@PreDestroy
	public void dispose() {
		fieldGroup.dispose();
	}

	protected void initialize() {
		detailViewClassName = mPart.getPersistedState().get(
				IConstants.DETAIL_VIEW_CLASS_NAME);
		String entityClassName = mPart.getPersistedState().get(
				IConstants.ENTITY_CLASS);
		eventTopic = Util.createEventSelectedTopic(entityClassName);

		try {
			entityClass = getBundleContext().getBundle().loadClass(
					entityClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		eventBroker.subscribe(eventTopic, this);
	}

	private BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(getClass()).getBundleContext();
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getTopic().contains(eventTopic)) {
			entity = event.getProperty(IEventBroker.DATA);
			// em.detach(entity);
			setEntity(entity);
		}
	}

	protected void setEntity(Object entity) {
		parentLayout.getUI().accessSynchronously(new Runnable() {
			@Override
			public void run() {
				BeanItem<Object> beanItem = new BeanItem<Object>(entity);
				fieldGroup.setItemDataSource(beanItem);
			}
		});
	
	}

	@Persist
	public void save() {
		EntityTransaction txn = em.getTransaction();
		try {
			fieldGroup.commit();

			txn.begin();
			entity = em.merge(entity);
			em.flush();
			txn.commit();
			em.detach(entity);
		} catch (Exception e1) {
			System.out.println(e1);
			txn.rollback();
		}

		refresh();

		eventBroker
				.send(Util.createEventEntitySavedTopic(entityClass.getName()),
						entity);
	}

	@NewEntity
	public void newEntity() {
		try {
			entity = entityClass.newInstance();
			setEntity(entity);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Refresh
	public void refresh() {
		entity = em.merge(entity);
		em.refresh(entity);
		setEntity(entity);
	}

	@Delete
	public void delete() {
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();
			entity = em.merge(entity);
			em.remove(entity);
			txn.commit();
		} catch (Exception e1) {
			System.out.println(e1);
			txn.rollback();
		}

		newEntity();

		eventBroker.send(
				Util.createEventEntityDeletedTopic(entityClass.getName()),
				entity);
	}

}
