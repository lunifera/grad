/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Division;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Division.class)
public class DivisionToHtmlConverter extends EntityToHtmlConverter<Division> {

	public DivisionToHtmlConverter() {
		super(Division.class);
	}

}
