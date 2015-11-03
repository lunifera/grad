package com.lunifera.graduate.ui.application.view;

import java.util.Collection;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.lunifera.graduate.ui.application.view.api.IInfoViewProvider;
import com.vaadin.ui.Component;

public class InfoView extends AbstractPartSensitiveView {

	protected Component getContent(MPart mPart) {
		BundleContext context = getBundleContext();

		String id = getServiceID(mPart);

		try {
			String filter = String.format("(%s=%s)",
					IInfoViewProvider.PROPERTY, id);
			Collection<ServiceReference<IInfoViewProvider>> services = context
					.getServiceReferences(IInfoViewProvider.class, filter);
			if (!services.isEmpty()) {
				ServiceReference<IInfoViewProvider> ref = services.iterator()
						.next();
				IInfoViewProvider service = context.getService(ref);
				if (service != null) {
					try {
						return service.getInfoContent(mPart);
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
		String id = mPart.getPersistedState().get(IInfoViewProvider.PROPERTY);
		if (id == null || id.isEmpty()) {
			id = mPart.getElementId();
		}
		return id;
	}
}
