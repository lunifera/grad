package com.lunifera.graduate.ui.application.custom.grid;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.lunifera.graduate.entities.Student;
import com.lunifera.graduate.ui.application.view.api.AbstractGridEnhancer;
import com.lunifera.graduate.ui.application.view.api.IGridViewEnhancer;

@Component(immediate = true, service = { IGridViewEnhancer.class }, property = { "entityClass=com.lunifera.graduate.entities.Student" })
public class StudentGridEnhancer extends AbstractGridEnhancer<Student> {

	private static final String[] HIDDEN_COLUMNS = new String[] { "uuid",
			"createdAt", "createdBy", "updatedAt", "updatetBy" };

	private static final String[] COLUMNS_ODER = new String[] { "version", "lfp",
			"number", "description", "title", "gender", "firstName",
			"firstName2", "lastName", "birthName", "birthday", "birthPlace",
			"birthCountry", "nationality", "phone", "cellPhone", "mailAddress",
			"homeAddress.street", "homeAddress.postalcode", "homeAddress.city",
			"semesterAddress.street", "semesterAddress.postalcode",
			"semesterAddress.city", "subjectOfStudy", "finalDegree",
			"completed", "completionDate", "completionCity" };

	private static final String[] NESTED_COLUMNS = new String[] {
			"homeAddress.street", "homeAddress.postalcode", "homeAddress.city",
			"semesterAddress.street", "semesterAddress.postalcode",
			"semesterAddress.city" };

	private static final String[] REMOVED_COLUMNS = new String[] {
			"homeAddress", "semesterAddress" };

	private static final Map<String, String[]> COLUMN_GROUPS;
	static {
		COLUMN_GROUPS = new HashMap<String, String[]>();

		// identifier group
		COLUMN_GROUPS.put("identifier", new String[] { "lfp", "number",
				"description" });

		// names group
		COLUMN_GROUPS.put("names", new String[] { "title", "gender",
				"firstName", "firstName2", "lastName", "birthName" });

		// birth group
		COLUMN_GROUPS.put("birth", new String[] { "birthday", "birthPlace",
				"birthCountry", "nationality" });

		// contacts group
		COLUMN_GROUPS.put("contacts", new String[] { "phone", "cellPhone",
				"mailAddress" });

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
		return COLUMNS_ODER;
	}

	@Override
	protected Map<String, String[]> getColumnGroups() {
		return COLUMN_GROUPS;
	}

}
