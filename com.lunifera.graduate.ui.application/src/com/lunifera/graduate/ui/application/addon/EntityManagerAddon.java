/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.application.addon;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;

import com.lunifera.graduate.ui.entitymanager.EMProvider;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public final class EntityManagerAddon {

	private EntityManager em;

	@PostConstruct
	public void init(IEclipseContext context) {

		MApplication application = context.get(MApplication.class);
		IEclipseContext appCtx = application.getContext();

		EntityManagerFactory emf = appCtx.get(EntityManagerFactory.class);
		if (emf != null) {
			em = emf.createEntityManager();
			appCtx.set(EntityManager.class, em);
		}

		EMProvider.setEntityManager(em);
	}

	@PreDestroy
	public void destroy() {
		EMProvider.setEntityManager(null);

		if (em != null) {
			em.close();
			em = null;
		}
	}
}
