package com.lunifera.graduate.ui.util;

import com.vaadin.server.ThemeResource;

public class Util {

	public static String createEventSelectedTopic(String entityName) {
		return String
				.format("entity/selected/%s", entityName.replace(".", "_"));
	}
	
	public static String createEventEntitySavedTopic(String entityName) {
		return String
				.format("entity/saved/%s", entityName.replace(".", "_"));
	}
	
	public static String createEventEntityDeletedTopic(String entityName) {
		return String
				.format("entity/deleted/%s", entityName.replace(".", "_"));
	}

	public static ThemeResource getEntityThemeResource(Class<?> entityClass) {
		return new ThemeResource(getEntityThemeResourcePath(entityClass));
	}

	public static String getEntityThemeResourcePath(Class<?> entityClass) {
		return "icons/" + entityClass.getClass().getSimpleName() + ".png";
	}

}
