package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.Division;

@SuppressWarnings("serial")
@EntityFieldAnnotation(Division.class)
public class DivisionField extends AbstractEntityField<Division> {

	public DivisionField() {
		super(Division.class);
	}

	@Override
	protected String getUUId(Division newValue) {
		return newValue.getUuid();
	}
}
