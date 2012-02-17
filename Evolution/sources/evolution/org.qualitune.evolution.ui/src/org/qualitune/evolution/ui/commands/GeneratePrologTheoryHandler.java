package org.qualitune.evolution.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.qualitune.evolution.prolog.generator.IPrologGenerator;

public class GeneratePrologTheoryHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection currentSelection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		Object firstElement = currentSelection.getFirstElement();
		if(firstElement instanceof IFile){
			IFile file = (IFile) firstElement;
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			if(!uri.isPlatformResource()){
				uri = URI.createPlatformResourceURI(uri.toString(), true);
			}
			ResourceSet rs = new ResourceSetImpl();
			Resource resource = rs.getResource(uri, true);
			IPrologGenerator generator = IPrologGenerator.INSTANCE;
			if(resource != null){
				for (EObject model : resource.getContents()) {
					generator.generateTheoryAndAddToKnowledgebase(model);
				}
			}
		}
		return null;
	}

}
