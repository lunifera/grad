package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.Degree;

@SuppressWarnings("serial")
@EntityFieldAnnotation(Degree.class)
public class DegreeField extends AbstractEntityField<Degree> {

	public DegreeField() {
		super(Degree.class);
	}

	@Override
	protected String getUUId(Degree newValue) {
		return newValue.getUuid();
	}
}
