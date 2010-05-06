package org.emftext.refactoring.ui;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.emftext.language.refactoring.refactoring_specification.CREATE;
import org.emftext.language.refactoring.refactoring_specification.Instruction;
import org.emftext.language.refactoring.refactoring_specification.RefactoringSpecification;
import org.emftext.language.refactoring.rolemapping.Mapping;
import org.emftext.language.refactoring.roles.Role;
import org.emftext.refactoring.editorconnector.IEditorConnector;
import org.emftext.refactoring.interpreter.IRefactorer;
import org.emftext.refactoring.interpreter.IRefactoringInterpreter;
import org.emftext.refactoring.ltk.ModelRefactoring;
import org.emftext.refactoring.ltk.ModelRefactoringWizard;

/**
 * This action can be registered to the context menus of editors
 * generated by EMFText to enable refactorings.
 */
public class RefactoringAction extends Action {

	private Mapping mapping;
	private IRefactorer refactorer;
	private EObject refactoredModel;
	private TransactionalEditingDomain diagramTransactionalEditingDomain;
	private IEditorPart activeEditor;
	private IEditorConnector connector;

	public RefactoringAction(Mapping mapping, IRefactorer refactorer, IEditorConnector connector) {
		super();
		this.mapping = mapping;
		this.refactorer = refactorer;
		this.connector = connector;
	}

	public RefactoringAction(Mapping mapping, IRefactorer refactorer, TransactionalEditingDomain diagramTransactionalEditingDomain, IEditorPart activeEditor, IEditorConnector connector) {
		this(mapping, refactorer, connector);
		this.diagramTransactionalEditingDomain = diagramTransactionalEditingDomain;
		this.activeEditor = activeEditor;
	}

	@Override
	public void run() {
		ltkRun();
	}
	
	public void ltkRun(){
		ModelRefactoring refactoring = new ModelRefactoring(refactorer, mapping, diagramTransactionalEditingDomain, getText(), activeEditor);
		ModelRefactoringWizard wizard = new ModelRefactoringWizard(refactoring);
		wizard.setWindowTitle(refactoring.getName());
		RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation(wizard);
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		try {
			int result = op.run(shell, getText());
			if(result == IDialogConstants.OK_ID){
				IRefactoringInterpreter interpreter = refactorer.getCurrentInterpreter();
				Map<Role, Object> roleRuntimeInstances = interpreter.getRoleRuntimeInstances();
				RefactoringSpecification refSpec = interpreter.getRefactoringSpecification();
				List<EObject> objectsToSelect = new LinkedList<EObject>();
				for (Role role : roleRuntimeInstances.keySet()) {
					List<Instruction> roleInstructions = refSpec.getInstructionsReferencingRole(role);
					for (Instruction instruction : roleInstructions) {
						if(instruction instanceof CREATE){
							Object instance = roleRuntimeInstances.get(role);
							if(instance instanceof EObject){
								objectsToSelect.add((EObject) instance);
							} else if(instance instanceof List<?>){
								objectsToSelect.addAll((List<EObject>) instance);
							}
						}
					}
				}
				connector.selectEObjects(objectsToSelect);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
