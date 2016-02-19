/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Grading;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Grading.class)
public class GradingToHtmlConverter extends EntityToHtmlConverter<Grading> {

	public GradingToHtmlConverter() {
		super(Grading.class);
	}

}
