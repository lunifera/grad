/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.application.view;

import java.util.Collection;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.lunifera.graduate.ui.application.view.api.IOutlineViewProvider;
import com.vaadin.ui.Component;

public class OutlineView extends AbstractPartSensitiveView {

	protected Component getContent(MPart mPart) {
		BundleContext context = getBundleContext();

		String id = getServiceID(mPart);

		try {
			String filter = String.format("(%s=%s)",
					IOutlineViewProvider.PROPERTY, id);
			Collection<ServiceReference<IOutlineViewProvider>> services = context
					.getServiceReferences(IOutlineViewProvider.class, filter);
			if (!services.isEmpty()) {
				ServiceReference<IOutlineViewProvider> ref = services
						.iterator().next();
				IOutlineViewProvider service = context.getService(ref);
				if (service != null) {
					try {
						return service.getOutlineContent(mPart);
					} finally {
						getBundleContext().ungetService(ref);
					}
				}
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns the ID to find a proper service.
	 * 
	 * @param mPart
	 * @return
	 */
	protected String getServiceID(MPart mPart) {
		String id = mPart.getPersistedState()
				.get(IOutlineViewProvider.PROPERTY);
		if (id == null || id.isEmpty()) {
			id = mPart.getElementId();
		}
		return id;
	}
}
