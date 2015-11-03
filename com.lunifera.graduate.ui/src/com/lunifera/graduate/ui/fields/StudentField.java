package com.lunifera.graduate.ui.fields;

import com.lunifera.graduate.entities.Student;

@SuppressWarnings("serial")
@EntityFieldAnnotation(Student.class)
public class StudentField extends AbstractEntityField<Student> {

	public StudentField() {
		super(Student.class);
	}

	@Override
	protected String getUUId(Student newValue) {
		return newValue.getUuid();
	}
}
