package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.Grading;

@SuppressWarnings("serial")
//@EntityField(Grading.class)
public class GradingField extends AbstractEntityField<Grading> {

	public GradingField() {
		super(Grading.class);
	}

	@Override
	protected String getUUId(Grading newValue) {
		return null;
	}

}
