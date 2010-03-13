package org.emftext.refactoring.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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
	private Resource resource;

	public RefactoringAction(Mapping mapping, IRefactorer refactorer, Resource resource) {
		super();
		this.mapping = mapping;
		this.refactorer = refactorer;
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		EObject refactoredModel = refactorer.refactor(mapping, false);
		// save or replace only modified parts 
		// --> have to wait for Mirkos and Jendriks ideas of replacement and not simply overwrite all contents
		try {
			resource.getContents().set(0, refactoredModel);
			resource.save(null);
			resource.setModified(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while saving refactored model");
		}
	}
}
