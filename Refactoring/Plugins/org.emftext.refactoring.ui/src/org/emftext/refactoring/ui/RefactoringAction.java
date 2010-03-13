package org.emftext.refactoring.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.internal.UIPlugin;
import org.emftext.access.EMFTextAccessProxy;
import org.emftext.access.resource.IEditor;
import org.emftext.access.resource.ILocationMap;
import org.emftext.access.resource.IResource;

/**
 * This action can be registered to the context menus of editors
 * generated by EMFText to enable refactorings.
 */
public class RefactoringAction extends Action implements IEditorActionDelegate  {

	private IEditorPart activeEditor;
	private int lastStartOffset;
	private int lastEndOffset;
	
	public RefactoringAction() {
		super();
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		activeEditor = targetEditor;
	}

	public void run(IAction action) {
		IEditor emftextEditor = (IEditor) EMFTextAccessProxy.get(activeEditor, IEditor.class);
		IResource resource = emftextEditor.getResource();
		ILocationMap locationMap = resource.getLocationMap();
		List<EObject> elementsBetween = new ArrayList<EObject>();
		if (lastStartOffset == lastEndOffset) {
			TextEditor textEditor = (TextEditor) activeEditor;
			ISelectionProvider selectionProvider = textEditor.getSelectionProvider();
			ISelection selection = selectionProvider.getSelection();
			if (selection instanceof ITextSelection) {
				ITextSelection textSelection = (ITextSelection) selection;
				int offset = textSelection.getOffset();
				elementsBetween = locationMap.getElementsAt(offset);
			}
		} else {
			elementsBetween = locationMap.getElementsBetween(lastStartOffset, lastEndOffset);
		}
		// print all selected elements (or elements at the cursor if
		// no selection is made)
		for (EObject eObject : elementsBetween) {
			System.out.println("Selected: " + eObject);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof ITextSelection) {
			ITextSelection textSelection = (ITextSelection) selection;
			lastStartOffset = textSelection.getOffset();
			lastEndOffset = lastStartOffset + textSelection.getLength();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		IEditor emftextEditor = (IEditor) EMFTextAccessProxy.get(activeEditor, IEditor.class);
		IResource resource = emftextEditor.getResource();
		ILocationMap locationMap = resource.getLocationMap();
		List<EObject> elementsBetween = new ArrayList<EObject>();
		if (lastStartOffset == lastEndOffset) {
			TextEditor textEditor = (TextEditor) activeEditor;
			ISelectionProvider selectionProvider = textEditor.getSelectionProvider();
			ISelection selection = selectionProvider.getSelection();
			if (selection instanceof ITextSelection) {
				ITextSelection textSelection = (ITextSelection) selection;
				int offset = textSelection.getOffset();
				elementsBetween = locationMap.getElementsAt(offset);
			}
		} else {
			elementsBetween = locationMap.getElementsBetween(lastStartOffset, lastEndOffset);
		}
		// print all selected elements (or elements at the cursor if
		// no selection is made)
		for (EObject eObject : elementsBetween) {
			System.out.println("Selected: " + eObject);
		}
	}
}
