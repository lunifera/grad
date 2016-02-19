/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.SubjectOfStudy;

@SuppressWarnings("serial")
@EntityConverterAnnotation(SubjectOfStudy.class)
public class SubjectOfStudyToHtmlConverter extends EntityToHtmlConverter<SubjectOfStudy> {

	public SubjectOfStudyToHtmlConverter() {
		super(SubjectOfStudy.class);
	}

}
