/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.converter;

import com.lunifera.graduate.entities.Title;

@SuppressWarnings("serial")
@EntityConverterAnnotation(Title.class)
public class TitleToHtmlConverter extends EntityToHtmlConverter<Title> {

	public TitleToHtmlConverter() {
		super(Title.class);
	}

}
