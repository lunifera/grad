package com.lunifera.graduate.ui.forms;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { … }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class DivisonDetailView extends VerticalLayout {
	protected TabSheet masterTabSheet;
	protected TextField number2;
	protected TextField lfp2;
	protected CheckBox internal2;
	protected Label address1;
	protected TextField homeAddress_street;
	protected TextField homeAddress_postalcode;
	protected TextField homeAddress_city;
	protected Label adress2;
	protected TextField semesterAddress_street;
	protected TextField semesterAddress_postalcode;
	protected TextField semesterAddress_city;

	public DivisonDetailView() {
		Design.read(this);
	}
}
