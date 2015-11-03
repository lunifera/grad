package com.lunifera.graduate.ui.application.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.lunifera.runtime.web.vaadin.common.resource.IResourceProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * A view that reacts for changes of the active MPart to update its contents.
 */
public abstract class AbstractPartSensitiveView {

	@Inject
	protected IEclipseContext eclipseContext;
	@Inject
	protected IEventBroker eventBroker;
	@Inject
	MApplication mApplication;
	@Inject
	protected MPart mPart;
	@Inject
	protected VerticalLayout parentLayout;
	@Inject
	protected org.eclipse.e4.core.services.events.IEventBroker e4EventBroker;
	@Inject
	protected IResourceProvider resourceProvider;
	@Inject
	protected EPartService partService;

	protected CssLayout layout;
	protected MPart activeMPart;
	protected String activeContentID;
	protected Component activeContent;

	public AbstractPartSensitiveView() {
		super();
	}

	@PostConstruct
	public void setup() {
		layout = new CssLayout();
		parentLayout.setMargin(true);
		parentLayout.setSpacing(true);
		parentLayout.addComponent(layout);

	}

	@PreDestroy
	public void destroy() {
		layout = null;
		activeMPart = null;
		activeContent = null;
	}

	@Inject
	private void setActive(@Active @Optional MPart mPart) {

		if (activeMPart == mPart) {
			// nothing to do. We already are up to date.
			return;
		}

		if (mPart == null) {
			// no mPart --> No content!
			resetActiveContent();
			return;
		}

		String contentID = getServiceID(mPart);
		if (contentID.equals(activeContentID)) {
			// nothing to do. We already are up to date.
			return;
		}

		// reset the old state
		resetActiveContent();

		activeMPart = mPart;
		activeContentID = contentID;
		this.activeContent = getContent(activeMPart);
		if (activeContent != null) {
			layout.addComponent(activeContent);
		}
	}

	/**
	 * Resets the active content.
	 */
	protected void resetActiveContent() {
		if (activeContent != null) {
			layout.removeComponent(activeContent);
			activeContent = null;
		}
		activeContentID = null;
		activeMPart = null;
	}

	/**
	 * Returns the content for the given MPart.
	 * 
	 * @param activeMPart
	 * @return
	 */
	protected abstract Component getContent(MPart activeMPart);

	/**
	 * Returns the ID that should be used to access content.
	 * 
	 * @param mPart
	 * @return
	 */
	protected abstract String getServiceID(MPart mPart);

	protected BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(getClass()).getBundleContext();
	}

}