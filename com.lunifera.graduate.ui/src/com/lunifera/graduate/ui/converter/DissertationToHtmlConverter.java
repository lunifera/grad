/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Dissertation;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Dissertation.class)
public class DissertationToHtmlConverter extends
		EntityToHtmlConverter<Dissertation> {

	public DissertationToHtmlConverter() {
		super(Dissertation.class);
	}

}
