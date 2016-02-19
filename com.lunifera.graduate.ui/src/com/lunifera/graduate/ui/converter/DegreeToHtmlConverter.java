/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Degree;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Degree.class)
public class DegreeToHtmlConverter extends EntityToHtmlConverter<Degree> {

	public DegreeToHtmlConverter() {
		super(Degree.class);
	}

}
