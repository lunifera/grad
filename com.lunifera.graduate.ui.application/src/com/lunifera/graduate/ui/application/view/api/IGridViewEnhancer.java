/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.application.view.api;

import com.lunifera.graduate.ui.fields.filter.GridCellFilter;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.Grid;

public interface IGridViewEnhancer<E> {

	/**
	 * Contributors may enhance the JPAContainer settings like properties,
	 * filters,...
	 * 
	 * @param container
	 */
	void enhanceContainer(JPAContainer<E> container);

	/**
	 * Contributors may enhance the grid settings like column order,...
	 * 
	 * @param entityClass
	 * @param grid
	 */
	void enhanceGrid(Class<E> entityClass, Grid grid);

	/**
	 * Contribute the grid filter fields.
	 * 
	 * @param filter
	 * @param entityClass
	 * @param grid
	 */
	void enhanceFilters(GridCellFilter filter, Class<E> entityClass, Grid grid);

}
