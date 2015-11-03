package com.lunifera.graduate.ui.fields;

import org.lunifera.runtime.web.vaadin.components.widget.LazyLoadingComboBox;

import com.lunifera.graduate.ui.entitymanager.EMProvider;
import com.lunifera.graduate.ui.util.Util;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

@SuppressWarnings("serial")
public abstract class AbstractEntityField<E> extends CustomField<E> {

	private final Class<E> type;
	private LazyLoadingComboBox combobox;
	private JPAContainer<E> container;
	private ObjectProperty<String> property;

	public AbstractEntityField(Class<E> type) {
		this.type = type;
	}

	@Override
	protected Component initContent() {
		combobox = new LazyLoadingComboBox("Student");
		combobox.setWidth("100%");
		container = JPAContainerFactory.makeNonCached(getType(),
				EMProvider.getEntityManager());
		combobox.setContainerDataSource(container);
		combobox.setFilteringMode(FilteringMode.CONTAINS);
		combobox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		combobox.setItemCaptionPropertyId("number");
		container.sort(new Object[] { "lfp" }, new boolean[] { true });

		combobox.setIcon(getIconResource());

		property = new ObjectProperty<String>(getEntityId(), String.class);
		combobox.setPropertyDataSource(property);

		property.addValueChangeListener(e -> {
			E entity = getEntity(e.getProperty().getValue());
			if (super.getValue() != entity) {
				super.setValue(entity);
			}
		});

		return combobox;
	}

	protected E getEntity(Object value) {
		EntityItem<E> item = container.getItem(value);
		return item != null ? item.getEntity() : null;
	}

	protected Resource getIconResource() {
		return Util.getEntityThemeResource(getType());
	}

	@Override
	public Class<E> getType() {
		return type;
	}

	@Override
	protected void setInternalValue(E newValue) {
		super.setInternalValue(newValue);

		if (property != null) {
			property.setValue(newValue != null ? getUUId(newValue) : null);
		}
	}

	/**
	 * Returns the id of the current entity.
	 * 
	 * @return
	 */
	protected String getEntityId() {
		E entity = super.getValue();
		return entity != null ? getUUId(entity) : null;
	}

	protected abstract String getUUId(E newValue);

}
