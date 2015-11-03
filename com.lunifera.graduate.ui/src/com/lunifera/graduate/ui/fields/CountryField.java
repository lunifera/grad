package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.Country;

@SuppressWarnings("serial")
@EntityFieldAnnotation(Country.class)
public class CountryField extends AbstractEntityField<Country> {

	public CountryField() {
		super(Country.class);
	}

	@Override
	protected String getUUId(Country newValue) {
		return newValue.getUuid();
	}
	
}
