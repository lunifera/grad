package com.lunifera.graduate.ui.converter;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings({ "unchecked" })
public class EntityConverterProvider {

	@SuppressWarnings("rawtypes")
	private static final Map map = new HashMap();
	static {
		Bundle bundle = FrameworkUtil.getBundle(EntityConverterProvider.class);
		Enumeration<URL> classes = bundle.findEntries("/", "*.class", true);
		while (classes.hasMoreElements()) {
			URL fieldClassURL = classes.nextElement();
			String fieldClassName = fieldClassURL.getFile()
					.replace(".class", "").replace("/", ".");
			int index = fieldClassName.indexOf("com.lunifera.graduate");
			fieldClassName = fieldClassName.substring(index);
			try {
				Class<?> fieldClazz = bundle.loadClass(fieldClassName);
				if (fieldClazz.isAnnotationPresent(EntityConverterAnnotation.class)) {
					EntityConverterAnnotation annotation = fieldClazz
							.getAnnotation(EntityConverterAnnotation.class);
					map.put(annotation.value(), fieldClazz);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns a converter for the given entity class.
	 * 
	 * @param entityClass
	 * @return
	 */
	public static <T> EntityToHtmlConverter<T> getConverterFor(
			Class<T> entityClass) {
		Class<EntityToHtmlConverter<T>> clazz = (Class<EntityToHtmlConverter<T>>) map
				.get(entityClass);
		if (clazz != null) {
			try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
