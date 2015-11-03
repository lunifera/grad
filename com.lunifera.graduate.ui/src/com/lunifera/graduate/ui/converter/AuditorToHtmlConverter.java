package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Auditor;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Auditor.class)
public class AuditorToHtmlConverter extends EntityToHtmlConverter<Auditor> {

	public AuditorToHtmlConverter() {
		super(Auditor.class);
	}

}
