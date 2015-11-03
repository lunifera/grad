package com.lunifera.graduate.ui.fields;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.vaadin.ui.Field;

@SuppressWarnings("unchecked")
public class EntityFieldCache {

	@SuppressWarnings("rawtypes")
	private static final Map map = new HashMap();
	static {
		Bundle bundle = FrameworkUtil.getBundle(EntityFieldCache.class);
		Enumeration<URL> classes = bundle.findEntries("/", "*.class", true);
		while (classes.hasMoreElements()) {
			URL fieldClassURL = classes.nextElement();
			String fieldClassName = fieldClassURL.getFile()
					.replace(".class", "").replace("/", ".");
			int index = fieldClassName.indexOf("com.lunifera.graduate");
			if (index < 0) {
				continue;
			}
			fieldClassName = fieldClassName.substring(index);
			try {
				Class<?> fieldClazz = bundle.loadClass(fieldClassName);
				if (fieldClazz.isAnnotationPresent(EntityFieldAnnotation.class)) {
					EntityFieldAnnotation annotation = fieldClazz
							.getAnnotation(EntityFieldAnnotation.class);
					map.put(annotation.value(), fieldClazz);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns a field for the given entity class.
	 * 
	 * @param entityClass
	 * @return
	 */
	public static <T> Field<T> getFieldFor(Class<T> entityClass) {
		Class<Field<T>> clazz = (Class<Field<T>>) map.get(entityClass);
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
