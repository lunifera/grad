package com.lunifera.graduate.ui.fields;

import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

/**
 * Should be used for grid editors.
 */
@SuppressWarnings("serial")
public class CustomGridEditorFieldGroupFieldFactory extends
		Grid.EditorFieldFactory {

	private static CustomGridEditorFieldGroupFieldFactory INSTANCE = new CustomGridEditorFieldGroupFieldFactory();

	public static CustomGridEditorFieldGroupFieldFactory get() {
		return INSTANCE;
	}

	private CustomGridEditorFieldGroupFieldFactory() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
