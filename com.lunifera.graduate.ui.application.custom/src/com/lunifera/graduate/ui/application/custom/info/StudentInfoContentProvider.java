package com.lunifera.graduate.ui.application.custom.info;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import com.lunifera.graduate.ui.application.view.api.IInfoViewProvider;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

@org.osgi.service.component.annotations.Component(immediate = true, property = { "infoViewId=com.lunifera.graduate.entities.Student" })
public class StudentInfoContentProvider implements IInfoViewProvider {

	@Override
	public Component getInfoContent(MPart mPart) {
		Label htmlContent = new Label();
		htmlContent.setContentMode(ContentMode.HTML);
		htmlContent.setSizeFull();
		
		
		
		return htmlContent;
	}
}
