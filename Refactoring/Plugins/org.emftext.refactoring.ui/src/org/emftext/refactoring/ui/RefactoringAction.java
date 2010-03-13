package org.emftext.refactoring.ui;

import org.eclipse.jface.action.Action;
import org.emftext.language.refactoring.rolemapping.Mapping;
import org.emftext.refactoring.interpreter.IRefactorer;

/**
 * This action can be registered to the context menus of editors
 * generated by EMFText to enable refactorings.
 */
public class RefactoringAction extends Action {

	private Mapping mapping;
	private IRefactorer refactorer;

	public RefactoringAction(Mapping mapping, IRefactorer refactorer) {
		super();
		this.mapping = mapping;
		this.refactorer = refactorer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		refactorer.refactor(mapping, false);
	}
}
