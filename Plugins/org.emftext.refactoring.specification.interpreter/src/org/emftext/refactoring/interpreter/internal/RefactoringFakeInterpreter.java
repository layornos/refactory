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
package org.emftext.refactoring.interpreter.internal;

import org.emftext.refactoring.interpreter.IRefactoringFakeInterpreter;
import org.emftext.refactoring.interpreter.IRefactoringInterpreter;

public class RefactoringFakeInterpreter extends RefactoringInterpreter implements IRefactoringFakeInterpreter {

	private IRefactoringInterpreter realInterpreter;
	
	public RefactoringFakeInterpreter(IRefactoringInterpreter realInterpreter){
		super(realInterpreter.getPostProcessor());
		this.realInterpreter = realInterpreter;
		initialize(realInterpreter.getRefactoringSpecification(), realInterpreter.getRoleMapping());
		setValueProviderFactory(realInterpreter.getValueProviderFactory());
	}

	public IRefactoringInterpreter getRealInterpreter() {
		return realInterpreter;
	}

	@Override
	public IRefactoringFakeInterpreter getFakeInterpreter() {
		return this;
	}
}
