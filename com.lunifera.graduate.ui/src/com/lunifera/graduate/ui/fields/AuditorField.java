package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.Auditor;

@SuppressWarnings("serial")
@EntityFieldAnnotation(Auditor.class)
public class AuditorField extends AbstractEntityField<Auditor> {

	public AuditorField() {
		super(Auditor.class);
	}

	@Override
	protected String getUUId(Auditor newValue) {
		return newValue.getUuid();
	}
	
}
