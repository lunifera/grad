/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.application.view;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;

import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class ExplorerView {

	@Inject
	MApplication mApplication;
	@Inject
	private VerticalLayout parentLayout;

	private Tree tree;
	private FilesystemContainer container;
	private Panel scrollable;

	@PostConstruct
	public void setup() {

		scrollable = new Panel();
		scrollable.setSizeFull();
		
		tree = new Tree();
		scrollable.setContent(tree);
		
		parentLayout.setMargin(true);
		parentLayout.setSpacing(true);
		parentLayout.addComponent(scrollable);

		container = new FilesystemContainer(new File(System.getProperty("user.home")), true);
		tree.setContainerDataSource(container);
		tree.setItemCaptionPropertyId(FilesystemContainer.PROPERTY_NAME);
		tree.setItemIconPropertyId(FilesystemContainer.PROPERTY_ICON);

	}

}
