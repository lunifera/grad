package com.lunifera.graduate.ui.forms;

import com.lunifera.graduate.ui.fields.AuditorField;
import com.lunifera.graduate.ui.fields.DivisionField;
import com.lunifera.graduate.ui.fields.StudentField;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
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
public class DissertationDetailView extends VerticalLayout {
	protected TabSheet masterTabSheet;
	protected TextField number;
	protected TextField lfp;
	protected StudentField student;
	protected TextArea topic;
	protected ComboBox auditorGrade1;
	protected AuditorField auditor1;
	protected ComboBox auditorGrade2;
	protected AuditorField auditor2;
	protected AuditorField selectedAuditor1;
	protected AuditorField selectedAuditor2;
	protected AuditorField dean;
	protected DivisionField deanDivision;
	protected TextField acceptedDate;
	protected TextField surrenderDate;
	protected TextField openingDate;
	protected TextField publicViewFrom;
	protected TextField publicViewUntil;
	protected TextField objectionDate;
	protected TextField closingSessionDate;
	protected TextField depositCopyDate;
	protected TextField certificateDate;
	protected TextField inivitationDiesDate;
	protected TextField diesAcademicusDate;
	protected TextField note;

	public DissertationDetailView() {
		Design.read(this);
	}
}
