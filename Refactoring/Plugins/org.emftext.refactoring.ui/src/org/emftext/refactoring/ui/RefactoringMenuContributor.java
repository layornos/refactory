package org.emftext.refactoring.ui;

import java.util.List;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;
import org.emftext.access.EMFTextAccessProxy;
import org.emftext.access.resource.IEditor;
import org.emftext.access.resource.ILocationMap;
import org.emftext.access.resource.IResource;
import org.emftext.language.refactoring.refactoring_specification.RefactoringSpecification;
import org.emftext.language.refactoring.rolemapping.Mapping;
import org.emftext.refactoring.interpreter.IRefactorer;
import org.emftext.refactoring.interpreter.RefactorerFactory;
import org.emftext.refactoring.registry.refactoringspecification.IRefactoringSpecificationRegistry;
import org.emftext.refactoring.util.StringUtil;

public class RefactoringMenuContributor extends ExtensionContributionFactory {

	private static final String CONTEXT_MENU_ENTRY_TEXT 	= "EMF Refactor";
	private static final String CONTEXT_MENU_ENTRY_ID 		= "org.emftext.refactoring.menu";

	public RefactoringMenuContributor() {
		// 
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
		IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if(!activePart.equals(activeEditor)){
			System.out.println("Workbenchpart '" + activePart.getTitle() + "' is not an editor");
			return;
		}
		ISelectionProvider selectionProvider = activeEditor.getEditorSite().getSelectionProvider();
		ISelection selection = selectionProvider.getSelection();
		List<EObject> selectedElements = null;
		Resource resource = null;
		if(selection instanceof StructuredSelection){
			List<?> temp = ((StructuredSelection) selection).toList();
			for (Object object : temp) {
				if(!(object instanceof EObject)){
					return;
				}
			}
			selectedElements = (List<EObject>)temp;
			resource = selectedElements.get(0).eResource();
		} else if(selection instanceof ITextSelection){
//			if(EMFTextAccessProxy.isAccessibleWith(activeEditor, IEditor.class)){
				try {

					IEditor emftextEditor = (IEditor) EMFTextAccessProxy.get(activeEditor, IEditor.class);
					IResource emftextResource = emftextEditor.getResource();
					resource = emftextResource;
					if(resource == null){
						throw new Exception("just jump to catch block");
					}
					ILocationMap locationMap = emftextResource.getLocationMap();
					ITextSelection textSelection = (ITextSelection) selection;
					int startOffset = textSelection.getOffset();
					int endOffset = startOffset + textSelection.getLength();
					if(startOffset == endOffset){
						selectedElements = locationMap.getElementsAt(startOffset);
					} else {
						selectedElements = locationMap.getElementsBetween(startOffset, endOffset);
					}
				} catch (Exception e) {
					// probably another non EMFText generated editor
					System.out.println(e.getMessage());
					System.out.println("Editor " + activeEditor.getTitle() + " doesn't get Refactoring menu");
				}
//			}
		}
		if(resource != null && selectedElements != null && selectedElements.size() >= 1){
			for (EObject eObject : selectedElements) {
				System.out.println("Selected: " + eObject);
			}
			IMenuManager rootMenu = new MenuManager(CONTEXT_MENU_ENTRY_TEXT, CONTEXT_MENU_ENTRY_ID);

			IRefactorer refactorer = RefactorerFactory.eINSTANCE.getRefactorer(resource);
			refactorer.setInput(selectedElements);
			if(refactorer != null){
				List<Mapping> mappings = refactorer.getPossibleMappings(1.0);
				boolean containsEntries = false;
				for (Mapping mapping : mappings) {
					RefactoringSpecification refSpec = IRefactoringSpecificationRegistry.INSTANCE.getRefSpec(mapping.getMappedRoleModel());
					if(refSpec != null){
						String refactoringName = StringUtil.convertCamelCaseToWords(mapping.getName());
						Action refactoringAction = new RefactoringAction(mapping, refactorer, resource);
						refactoringAction.setText(refactoringName);
						rootMenu.add(refactoringAction);
						containsEntries = true;
					}
				}
				if(containsEntries){
					additions.addContributionItem(rootMenu, null);
				}
			}
		}
	}

}
