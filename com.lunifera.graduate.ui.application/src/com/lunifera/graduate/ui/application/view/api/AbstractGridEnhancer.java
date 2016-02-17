package com.lunifera.graduate.ui.application.view.api;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;

import org.vaadin.gridutil.renderer.BooleanRenderer;

import com.lunifera.graduate.ui.converter.EntityConverterProvider;
import com.lunifera.graduate.ui.converter.EntityToHtmlConverter;
import com.lunifera.graduate.ui.fields.CustomGridEditorFieldGroupFieldFactory;
import com.lunifera.graduate.ui.fields.filter.GridCellFilter;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.renderers.HtmlRenderer;

public abstract class AbstractGridEnhancer<ENTITY> implements
		IGridViewEnhancer<ENTITY> {

	public AbstractGridEnhancer() {
		super();
	}

	@Override
	public void enhanceGrid(Class<ENTITY> entityClass, Grid grid) {

		// set the editorFieldFactory
		grid.setEditorFieldFactory(CustomGridEditorFieldGroupFieldFactory.get());

		createHiddenColumns(getHiddenColumns(), entityClass, grid);
		createColumnsOrder(getColumnsOrder(), entityClass, grid);
		createColumnGroups(getColumnGroups(), entityClass, grid);
		createConverterAndRenderer(grid);
	}

	@Override
	public void enhanceContainer(JPAContainer<ENTITY> container) {

		container.setWriteThrough(true);

		removeColumns(container);

		addNestedColumns(container);

		createSortPropertyForEntities(container);
	}

	@Override
	public void enhanceFilters(GridCellFilter filter,
			Class<ENTITY> entityClass, Grid grid) {
		JPAContainer<?> container = (JPAContainer<?>) grid
				.getContainerDataSource();
		for (Column column : grid.getColumns()) {
			Class<?> propertyType = container.getType(column.getPropertyId());
			if (Number.class.isAssignableFrom(propertyType)) {
				filter.setNumberFilter(column.getPropertyId());
			} else if (propertyType == String.class) {
				filter.setTextFilter(column.getPropertyId(), true, false);
			} else if (propertyType == Date.class) {
				filter.setDateFilter(column.getPropertyId());
			} else if (propertyType.isEnum()) {
				filter.setComboBoxFilter(column.getPropertyId(),
						Arrays.asList(propertyType.getEnumConstants()));
			} else if (propertyType.isAnnotationPresent(Entity.class)) {
				filter.setEntityFilter(column.getPropertyId(), propertyType, "");
			}
		}
	}

	/**
	 * Defines the sort order property for entities.<br>
	 * For instance <code>Student#title</code> is an entity and so it is not
	 * sortable. By replacing property <code>title</code> by
	 * <code>title.number</code> as a sort property, the <code>title</code> will
	 * be sortable by its <code>number</code>.
	 * 
	 * @param container
	 */
	protected void createSortPropertyForEntities(JPAContainer<ENTITY> container) {
		for (String propertyId : container.getContainerPropertyIds()) {
			Class<?> propertyType = container.getType(propertyId);
			if (propertyType.isAnnotationPresent(Entity.class)) {
				container.setSortProperty(propertyId, propertyId + ".number");
			}
		}
	}

	/**
	 * Removes columns that are not required anymore. For instance an
	 * {@link javax.persistence.Embeddable @Embeddable} root.<br>
	 * Person#homeAddress should become remove. And therefore nestedColumns
	 * should be added. See {@link #addNestedColumns(JPAContainer)}
	 * 
	 * @param container
	 */
	protected void removeColumns(JPAContainer<ENTITY> container) {
		for (String nestedProperty : getRemovedProperties()) {
			container.removeContainerProperty(nestedProperty);
		}
	}

	/**
	 * Adds nested columns to the container. For instance the properties for an
	 * {@link javax.persistence.Embeddable @Embeddable} root.<br>
	 * Person#homeAddress may become removed by
	 * {@link #removeColumns(JPAContainer)} and instead
	 * <code>homeAddress.street</code>, <code>#homeAddress.postalcode</code>,...
	 * are added.
	 * 
	 * @param container
	 */
	protected void addNestedColumns(JPAContainer<ENTITY> container) {
		for (String nestedProperty : getAddedNestedProperties()) {
			container.addNestedContainerProperty(nestedProperty);
		}
	}

	/**
	 * Creates converters and renderers for a grid column.
	 * 
	 * @param grid
	 */
	protected void createConverterAndRenderer(Grid grid) {
		JPAContainer<?> container = (JPAContainer<?>) grid
				.getContainerDataSource();
		for (Column column : grid.getColumns()) {
			Class<?> propertyType = container.getType(column.getPropertyId());
			if (Boolean.class.isAssignableFrom(propertyType)) {
				column.setRenderer(new BooleanRenderer());
			} else {
				EntityToHtmlConverter<?> converter = EntityConverterProvider
						.getConverterFor(propertyType);
				if (converter != null) {
					column.setConverter(converter);
					column.setRenderer(new HtmlRenderer(""));
					column.setSortable(true);
				}
			}
		}
	}

	/**
	 * A column group is an additional header with joined columns.<br>
	 * For instance:
	 * <code>"nameGroup"->{"firstname", "firstname2", "lastname"}</code> will
	 * become joined under a common column called <code>nameGroup</code>.
	 * 
	 * @param columnGroups
	 * @param entityClass
	 * @param grid
	 */
	protected void createColumnGroups(Map<String, String[]> columnGroups,
			Class<ENTITY> entityClass, Grid grid) {
		HeaderRow headerRow = grid.prependHeaderRow();
		for (String group : columnGroups.keySet()) {
			HeaderCell cell = headerRow
					.join((Object[]) columnGroups.get(group));
			cell.setText(group);
		}
	}

	/**
	 * Defines the order of columns.
	 * 
	 * @param columnOrders
	 * @param entityClass
	 * @param grid
	 */
	protected void createColumnsOrder(String[] columnOrders,
			Class<ENTITY> entityClass, Grid grid) {
		grid.setColumnOrder((Object[]) columnOrders);
	}

	/**
	 * Creates hidden columns based on the meta info from entityClass.
	 * 
	 * @param entityClass
	 * @param grid
	 */
	protected void createHiddenColumns(String[] hiddenColumns,
			Class<ENTITY> entityClass, Grid grid) {
		for (String columnId : hiddenColumns) {
			Column column = grid.getColumn(columnId);
			column.setHidden(true);
		}
	}

	/**
	 * Returns all nested properties that should be added.
	 * 
	 * @return
	 */
	protected abstract String[] getAddedNestedProperties();

	/**
	 * Returns all properties that should be removed.
	 * 
	 * @return
	 */
	protected abstract String[] getRemovedProperties();

	/**
	 * Returns all columns that are hidden.
	 * 
	 * @return
	 */
	protected abstract String[] getHiddenColumns();

	/**
	 * Returns the order of columns.
	 * 
	 * @return
	 */
	protected abstract String[] getColumnsOrder();

	/**
	 * Returns the column groups.
	 * 
	 * @return
	 */
	protected abstract Map<String, String[]> getColumnGroups();
}