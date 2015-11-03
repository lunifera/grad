package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.Title;

@SuppressWarnings("serial")
@EntityFieldAnnotation(Title.class)
public class TitleField extends AbstractEntityField<Title> {

	public TitleField() {
		super(Title.class);
	}

	@Override
	protected String getUUId(Title newValue) {
		return newValue.getUuid();
	}
}
