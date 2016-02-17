package com.lunifera.graduate.ui.application.view;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.lunifera.runtime.web.vaadin.common.resource.IResourceProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lunifera.graduate.ui.application.annotations.Refresh;
import com.lunifera.graduate.ui.application.view.api.IConstants;
import com.lunifera.graduate.ui.application.view.api.IGridViewEnhancer;
import com.lunifera.graduate.ui.fields.filter.GridCellFilter;
import com.lunifera.graduate.ui.util.Util;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.event.SelectionEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.VerticalLayout;

public class GenericGridPartView {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GenericGridPartView.class);

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

	private Grid grid;
	private JPAContainer<?> container;

	private GridCellFilter filters;

	public GenericGridPartView() {
	}

	@PostConstruct
	public void setup() {

		initialize();

		parentLayout.setMargin(true);

//		Button b = new Button("refresh");
//		parentLayout.addComponent(b);
//		b.addClickListener(e -> container.refresh());

		grid = new Grid();
		grid.setSizeFull();
		grid.setEditorEnabled(true);
		grid.setHeaderVisible(true);
		grid.setFooterVisible(true);
		grid.setColumnReorderingAllowed(true);

		filters = new GridCellFilter(grid);

		parentLayout.addComponent(grid);
		parentLayout.setExpandRatio(grid, 1.0f);

		container = JPAContainerFactory.makeBatchable(entityClass, em);
		enhanceContainer(container);

		grid.setContainerDataSource(container);

		// enhance the grid
		//
		enhanceGrid();

		grid.addSelectionListener(e -> entitySelected(e));
	}

	@Refresh
	protected void refresh() {
		container.refresh();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void enhanceGrid() {
		BundleContext context = getBundleContext();
		try {
			Collection<ServiceReference<IGridViewEnhancer>> services = context
					.getServiceReferences(IGridViewEnhancer.class,
							"(entityClass=" + entityClass.getName() + ")");
			if (!services.isEmpty()) {
				ServiceReference<IGridViewEnhancer> ref = services.iterator()
						.next();
				IGridViewEnhancer service = context.getService(ref);
				if (service != null) {
					try {
						service.enhanceGrid(entityClass, grid);
						service.enhanceFilters(filters, entityClass, grid);
					} finally {
						getBundleContext().ungetService(ref);
					}
				}
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void enhanceContainer(JPAContainer<?> container) {
		BundleContext context = getBundleContext();
		try {
			Collection<ServiceReference<IGridViewEnhancer>> services = context
					.getServiceReferences(IGridViewEnhancer.class,
							"(entityClass=" + entityClass.getName() + ")");
			if (!services.isEmpty()) {
				ServiceReference<IGridViewEnhancer> ref = services.iterator()
						.next();
				IGridViewEnhancer service = context.getService(ref);
				if (service != null) {
					try {
						service.enhanceContainer(container);
					} finally {
						getBundleContext().ungetService(ref);
					}
				}
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
	}

	private void entitySelected(SelectionEvent e) {
		SingleSelectionModel model = (SingleSelectionModel) grid
				.getSelectionModel();
		if (model.getSelectedRow() == null) {
			return;
		}
		Object entity = container.getItem(model.getSelectedRow()).getEntity();

		eventBroker.post(eventTopic, entity);
	}

	protected void initialize() {
		String entityClassName = mPart.getPersistedState().get(
				IConstants.ENTITY_CLASS);
		eventTopic = Util.createEventSelectedTopic(entityClassName);

		try {
			entityClass = getBundleContext().getBundle().loadClass(
					entityClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		eventBroker.subscribe(
				Util.createEventEntitySavedTopic(entityClassName), e -> {
					container.refresh();
				});

		eventBroker.subscribe(
				Util.createEventEntityDeletedTopic(entityClassName), e -> {
					container.refresh();
				});
	}

	private BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(getClass()).getBundleContext();
	}

}
