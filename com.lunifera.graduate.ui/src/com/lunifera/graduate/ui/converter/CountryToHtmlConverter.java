package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Country;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Country.class)
public class CountryToHtmlConverter extends EntityToHtmlConverter<Country> {

	public CountryToHtmlConverter() {
		super(Country.class);
	}

}
