package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.Dissertation;

@SuppressWarnings("serial")
@EntityFieldAnnotation(Dissertation.class)
public class DissertationField extends AbstractEntityField<Dissertation> {

	public DissertationField() {
		super(Dissertation.class);
	}

	@Override
	protected String getUUId(Dissertation newValue) {
		return newValue.getUuid();
	}
}
