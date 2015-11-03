package com.lunifera.graduate.ui.forms;

import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design.DefaultComponentFactory;
import com.vaadin.ui.declarative.DesignContext;

@SuppressWarnings("serial")
public class ComponentFactory extends DefaultComponentFactory {

	@SuppressWarnings("unchecked")
	@Override
	protected Class<? extends Component> resolveComponentClass(
			String qualifiedClassName, DesignContext context) {
		if (qualifiedClassName.startsWith("com.lunifera.graduate.ui.fields")) {
			try {
				return (Class<? extends Component>) getClass().getClassLoader()
						.loadClass(qualifiedClassName);
			} catch (ClassNotFoundException e) {
				return null;
			}
		}
		return super.resolveComponentClass(qualifiedClassName, context);
	}
}
