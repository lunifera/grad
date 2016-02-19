/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.application.handler;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

public class SaveEntityHandler {

	@Execute
	public void execute(@Active MContext context, @Active MPart part,
			ParameterizedCommand command) {
		ContextInjectionFactory.invoke(part.getObject(), Persist.class,
				context.getContext(), null);
	}

	@CanExecute
	public boolean canExeute() {
		return true;
	}

}
