package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Degree;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Degree.class)
public class DegreeToHtmlConverter extends EntityToHtmlConverter<Degree> {

	public DegreeToHtmlConverter() {
		super(Degree.class);
	}

}
