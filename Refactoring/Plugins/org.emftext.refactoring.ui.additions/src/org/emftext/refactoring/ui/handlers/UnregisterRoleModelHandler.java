/*******************************************************************************
 * Copyright (c) 2006-2012
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Berlin, Amtsgericht Charlottenburg, HRB 140026
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Berlin, Germany
 *      - initial API and implementation
 ******************************************************************************/
package org.emftext.refactoring.ui.handlers;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.emftext.language.refactoring.roles.RoleModel;
import org.emftext.language.refactoring.roles.resource.rolestext.IRolestextTextResource;
import org.emftext.language.refactoring.roles.resource.rolestext.ui.RolestextEditor;
import org.emftext.refactoring.registry.rolemodel.IRoleModelRegistry;

public class UnregisterRoleModelHandler extends AbstractModelHandler<RoleModel, IRolestextTextResource, RolestextEditor> {

	@Override
	protected RoleModel getModelFromSelection(ITextSelection selection, IRolestextTextResource resource) {
		return (RoleModel) resource.getContents().get(0);
	}

	@Override
	protected boolean isModelEditorInstance(IEditorPart editor) {
		return editor instanceof RolestextEditor;
	}

	@Override
	protected IRolestextTextResource getResource(RolestextEditor editor) {
		return editor.getResource();
	}

	@Override
	protected void executeWithModel(RoleModel model) {
		IRoleModelRegistry.INSTANCE.unregisterRoleModel(model);
	}

}
