/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.application.custom.grid;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.lunifera.graduate.entities.Student;
import com.lunifera.graduate.ui.application.view.api.AbstractGridEnhancer;
import com.lunifera.graduate.ui.application.view.api.IGridViewEnhancer;

@Component(immediate = true, service = { IGridViewEnhancer.class }, property = { "entityClass=com.lunifera.graduate.entities.Student" })
public class StudentGridEnhancer extends AbstractGridEnhancer<Student> {

	private static final String[] HIDDEN_COLUMNS = new String[] { "lfp",
			"number", "description", "gender", "firstName2", "birthName",
			"nationality", "phone", "mailAddress", "createdAt", "createdBy",
			"updatedAt", "updatetBy" };

	private static final String[] COLUMNS_ORDER = new String[] { "title",
			"firstName", "lastName", "birthday", "birthPlace", "birthCountry",
			"cellPhone", "homeAddress.street", "homeAddress.postalcode",
			"homeAddress.city", "semesterAddress.street",
			"semesterAddress.postalcode", "semesterAddress.city",
			"subjectOfStudy", "finalDegree", "completed", "completionDate",
			"completionCity" };

	private static final String[] NESTED_COLUMNS = new String[] {
			"homeAddress.street", "homeAddress.postalcode", "homeAddress.city",
			"semesterAddress.street", "semesterAddress.postalcode",
			"semesterAddress.city" };

	private static final String[] REMOVED_COLUMNS = new String[] { "version",
			"uuid", "homeAddress", "semesterAddress" };

	private static final Map<String, String[]> COLUMN_GROUPS;
	static {
		COLUMN_GROUPS = new HashMap<String, String[]>();

		// names group
		COLUMN_GROUPS.put("names", new String[] { "title", "firstName",
				"lastName" });

		// birth group
		COLUMN_GROUPS.put("birth", new String[] { "birthday", "birthPlace",
				"birthCountry" });
		// home address group
		COLUMN_GROUPS.put("homeAddress", new String[] { "homeAddress.street",
				"homeAddress.postalcode", "homeAddress.city" });
		// semesterAddress address group
		COLUMN_GROUPS.put("semesterAddress", new String[] {
				"semesterAddress.street", "semesterAddress.postalcode",
				"semesterAddress.city" });
		// study group
		COLUMN_GROUPS.put("study",
				new String[] { "subjectOfStudy", "finalDegree", "completed",
						"completionDate", "completionCity" });
	}

	protected String[] getAddedNestedProperties() {
		return NESTED_COLUMNS;
	}

	protected String[] getRemovedProperties() {
		return REMOVED_COLUMNS;
	}

	@Override
	protected String[] getHiddenColumns() {
		return HIDDEN_COLUMNS;
	}

	@Override
	protected String[] getColumnsOrder() {
		return COLUMNS_ORDER;
	}

	@Override
	protected Map<String, String[]> getColumnGroups() {
		return COLUMN_GROUPS;
	}

}
