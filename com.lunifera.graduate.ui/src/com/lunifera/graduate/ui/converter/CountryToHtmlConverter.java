/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Country;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Country.class)
public class CountryToHtmlConverter extends EntityToHtmlConverter<Country> {

	public CountryToHtmlConverter() {
		super(Country.class);
	}

}
