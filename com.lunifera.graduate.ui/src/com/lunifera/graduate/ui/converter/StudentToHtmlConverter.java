/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Student;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Student.class)
public class StudentToHtmlConverter extends EntityToHtmlConverter<Student> {

	public StudentToHtmlConverter() {
		super(Student.class);
	}

}
