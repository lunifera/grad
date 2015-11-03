package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Title;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Title.class)
public class TitleToHtmlConverter extends EntityToHtmlConverter<Title> {

	public TitleToHtmlConverter() {
		super(Title.class);
	}

}
