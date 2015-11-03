package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.SubjectOfStudy;

@SuppressWarnings("serial")
@EntityConverterAnnotation(SubjectOfStudy.class)
public class SubjectOfStudyToHtmlConverter extends EntityToHtmlConverter<SubjectOfStudy> {

	public SubjectOfStudyToHtmlConverter() {
		super(SubjectOfStudy.class);
	}

}
