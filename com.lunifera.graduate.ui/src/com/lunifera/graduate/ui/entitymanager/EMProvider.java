package com.lunifera.graduate.ui.entitymanager;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.vaadin.ui.UI;

public class EMProvider {

	/**
	 * Delegates to the Vaadin UI to get the proper EntityManager.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static EntityManager getEntityManager() {
		UI ui = UI.getCurrent();
		if (ui.getData() != null) {
			Map<Object, Object> map = (Map<Object, Object>) ui.getData();
			return (EntityManager) map.get(EntityManager.class);
		}
		return null;
	}

	public static void setEntityManager(EntityManager entityManager) {
		UI ui = UI.getCurrent();
		if (ui.getData() == null) {
			ui.setData(new HashMap<Object, Object>());
		}

		@SuppressWarnings("unchecked")
		Map<Object, Object> map = (Map<Object, Object>) ui.getData();
		if (entityManager != null) {
			map.put(EntityManager.class, entityManager);
		} else {
			map.remove(EntityManager.class);
		}
	}

}
