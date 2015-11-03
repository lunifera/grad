package com.lunifera.graduate.ui.application.view.api;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import com.vaadin.ui.Component;

/**
 * Provides outlines for a defined ID. Use the {@link #SERVICE_PROP} as an
 * property in the OSGi service with an ID.
 * <p>
 * ID follows following semantic:<br>
 * <ol>
 * <li>If the MPart has a persistent property "outlineViewId", then this ID is
 * used to find a proper IOutlineViewProvider</li>
 * <li>Otherwise the elementID of the MPart is used to find a proper
 * IOutlineViewProvider.</li>
 * </ol>
 */
public interface IOutlineViewProvider {

	/**
	 * Use this property in the OSGi service and the MPart's persistent
	 * properties with an ID.
	 */
	public static final String PROPERTY = "outlineViewId";

	/**
	 * Returns the outline content for the given mPart.
	 * 
	 * @param mPart
	 * @return
	 */
	Component getOutlineContent(MPart mPart);

}
