package org.emftext.refactoring.ui;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.emftext.language.refactoring.refactoring_specification.CREATE;
import org.emftext.language.refactoring.refactoring_specification.Instruction;
import org.emftext.language.refactoring.refactoring_specification.MOVE;
import org.emftext.language.refactoring.refactoring_specification.RefactoringSpecification;
import org.emftext.language.refactoring.rolemapping.RoleMapping;
import org.emftext.language.refactoring.roles.Role;
import org.emftext.refactoring.editorconnector.IEditorConnector;
import org.emftext.refactoring.interpreter.IRefactorer;
import org.emftext.refactoring.interpreter.IRefactoringInterpreter;
import org.emftext.refactoring.ltk.ModelRefactoring;
import org.emftext.refactoring.ltk.ModelRefactoringWizard;
import org.emftext.refactoring.util.StringUtil;

/**
 * This action can be registered to the context menus of editors
 * generated by EMFText to enable refactorings.
 */
public class RefactoringAction extends Action {

	private RoleMapping mapping;
	private IRefactorer refactorer;
	private EObject refactoredModel;
	private TransactionalEditingDomain diagramTransactionalEditingDomain;
	private IEditorPart activeEditor;
	private IEditorConnector connector;

	public RefactoringAction(RoleMapping mapping, IRefactorer refactorer, IEditorConnector connector) {
		super();
		this.mapping = mapping;
		this.refactorer = refactorer;
		this.connector = connector;
	}

	public RefactoringAction(RoleMapping mapping, IRefactorer refactorer, TransactionalEditingDomain diagramTransactionalEditingDomain, IEditorPart activeEditor, IEditorConnector connector) {
		this(mapping, refactorer, connector);
		this.diagramTransactionalEditingDomain = diagramTransactionalEditingDomain;
		this.activeEditor = activeEditor;
	}

	@Override
	public void run() {
		ltkRun();
	}

	@Override
	public String getText() {
		return StringUtil.convertCamelCaseToWords(mapping.getName());
	}

	public void ltkRun() {
		EObject original = refactorer.getOriginalModel();
		ModelRefactoring refactoring = new ModelRefactoring(refactorer, mapping, diagramTransactionalEditingDomain, getText(), activeEditor);
		ModelRefactoringWizard wizard = new ModelRefactoringWizard(refactoring);
		wizard.setWindowTitle(refactoring.getName());
		RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation(wizard);
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		try {
			int result = op.run(shell, getText());
			if(connector != null){
				if (result == IDialogConstants.OK_ID) {
					IRefactoringInterpreter interpreter = refactorer.getCurrentInterpreter();
					interpreter = refactoring.getRefactorer().getCurrentInterpreter();;
					Map<Role, List<URI>> roleRuntimeInstanceURIs = interpreter.getRoleRuntimeInstanceURIs();
					Map<Role, List<EObject>> resolvedRoleRuntimeInstances = postSaveRuntimeInstanceHandling(roleRuntimeInstanceURIs, refactorer.getResource().getResourceSet());
					RefactoringSpecification refSpec = interpreter.getRefactoringSpecification();
					List<EObject> objectsToSelect = new LinkedList<EObject>();
					for (Role role : resolvedRoleRuntimeInstances.keySet()) {
						List<Instruction> roleInstructions = refSpec.getInstructionsReferencingRole(role);
						for (Instruction instruction : roleInstructions) {
							if (instruction instanceof CREATE || instruction instanceof MOVE) {
								List<EObject> instance = resolvedRoleRuntimeInstances.get(role);
								objectsToSelect.addAll(instance);
							}
						}
					}
					connector.selectEObjects(objectsToSelect);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Map<Role, List<EObject>> postSaveRuntimeInstanceHandling(Map<Role, List<URI>> roleRuntimeInstanceURIs, ResourceSet resourceSet) {
		Map<Role, List<EObject>> resolvedRoleRuntimeInstances = new LinkedHashMap<Role, List<EObject>>();
		for (Role role : roleRuntimeInstanceURIs.keySet()) {
			List<URI> uriList = roleRuntimeInstanceURIs.get(role);
			List<EObject> resolvedRuntimeInstances = new LinkedList<EObject>();
			for (URI uri : uriList) {
				try {
					EObject resolvedObject = resourceSet.getEObject(uri, false);
					resolvedRuntimeInstances.add(resolvedObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			resolvedRoleRuntimeInstances.put(role, resolvedRuntimeInstances);
		}
		return resolvedRoleRuntimeInstances;
	}
}
