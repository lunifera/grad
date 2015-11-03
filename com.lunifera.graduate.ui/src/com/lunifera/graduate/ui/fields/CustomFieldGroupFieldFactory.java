package com.lunifera.graduate.ui.fields;

import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

@SuppressWarnings({ "unchecked", "serial" })
public class CustomFieldGroupFieldFactory extends DefaultFieldGroupFieldFactory {

	private static CustomFieldGroupFieldFactory INSTANCE = new CustomFieldGroupFieldFactory();

	public static CustomFieldGroupFieldFactory get() {
		return INSTANCE;
	}

	private CustomFieldGroupFieldFactory() {
	}

	@SuppressWarnings("rawtypes")
	public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
		Field<?> field = EntityFieldCache.getFieldFor(type);
		if (field != null) {
			return (T) field;
		}

		field = super.createField(type, fieldType);
		if (field instanceof TextField) {
			((TextField) field).setNullRepresentation("");
		}
		return (T) field;
	}

}
