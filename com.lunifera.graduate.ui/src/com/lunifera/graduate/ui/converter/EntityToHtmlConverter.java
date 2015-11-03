package com.lunifera.graduate.ui.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.vaadin.data.util.converter.Converter;

@SuppressWarnings("serial")
public class EntityToHtmlConverter<MODEL> implements Converter<String, MODEL> {

	public static final String GET_NUMBER = "getNumber";
	protected static final String GET_DESCRIPTION = "getDescription";
	private static final String PATTERN = "<b>%s</b>&nbsp-&nbsp<i>%s</i>";
	private Class<MODEL> modelType;

	public EntityToHtmlConverter(Class<MODEL> modelType) {
		this.modelType = modelType;
	}

	@Override
	public Class<MODEL> getModelType() {
		return modelType;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

	@Override
	public MODEL convertToModel(String value,
			Class<? extends MODEL> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String convertToPresentation(MODEL value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null) {
			return "";
		}

		Method numberGetter = MethodUtils.getAccessibleMethod(modelType,
				numberGetterName(), new Class[0]);
		Method descriptionGetter = MethodUtils.getAccessibleMethod(modelType,
				descriptionGetterName(), new Class[0]);

		try {
			return String.format(PATTERN,
					numberGetter.invoke(value, new Object[0]),
					descriptionGetter.invoke(value, new Object[0]));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		throw new IllegalStateException();
	}

	protected String descriptionGetterName() {
		return GET_DESCRIPTION;
	}

	protected String numberGetterName() {
		return GET_NUMBER;
	}

}