package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.SubjectOfStudy;

@SuppressWarnings("serial")
@EntityFieldAnnotation(SubjectOfStudy.class)
public class SubjectOfStudyField extends AbstractEntityField<SubjectOfStudy> {

	public SubjectOfStudyField() {
		super(SubjectOfStudy.class);
	}

	@Override
	protected String getUUId(SubjectOfStudy newValue) {
		return newValue.getUuid();
	}
}
