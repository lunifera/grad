package com.lunifera.graduate.ui.fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityFieldAnnotation {
	/**
	 * The entity class the annotated field is for.
	 * 
	 * @return
	 */
	Class<?> value();
}
