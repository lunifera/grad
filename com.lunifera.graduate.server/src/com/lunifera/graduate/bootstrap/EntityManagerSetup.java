/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.bootstrap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * Ensures that the entity manager setup (drop and create table) is done
 * directly on startup.
 */
@Component
public class EntityManagerSetup {

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	protected void bind(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		if (em != null) {
			em.close();
		}
	}

	protected void unbind(EntityManagerFactory emf) {

	}

}
