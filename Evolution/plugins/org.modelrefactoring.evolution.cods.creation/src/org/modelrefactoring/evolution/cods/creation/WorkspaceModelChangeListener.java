/**
 * 
 */
package org.modelrefactoring.evolution.cods.creation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelrefactoring.evolution.megamodel.architecture.InstanceModel;
import org.modelrefactoring.evolution.megamodel.cods.CODS;
import org.modelrefactoring.evolution.registry.IKnowledgeBase;
import org.modelrefactoring.evolution.registry.IKnowledgeBaseRegistry;

/**
 * @author jreimann
 *
 */
public class WorkspaceModelChangeListener implements IResourceChangeListener, IResourceDeltaVisitor {

	private CODS megamodel;

	public WorkspaceModelChangeListener(CODS megaModel) {
		this.megamodel = megaModel;
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		//		IResource resource = event.getResource();
		switch (event.getType()) {
		case IResourceChangeEvent.PRE_CLOSE:
			//			System.out.println("close");
			break;

		case IResourceChangeEvent.PRE_DELETE:
			//			System.out.println("delete");
			break;

		case IResourceChangeEvent.POST_CHANGE:
			//			System.out.println("Resources have changed.");
			try {
				event.getDelta().accept(this);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource res = delta.getResource();
		if(res.getType() == IResource.FOLDER){
			IFolder folder = (IFolder) res.getAdapter(IFolder.class);
			if(isResourceHidden(folder)){
				return false;
			}
		} else {
			IFile file = (IFile) res.getAdapter(IFile.class);
			if(file != null){
				if(!isResourceHidden(file)){

					switch (delta.getKind()) {
					case IResourceDelta.ADDED:
						//			System.out.print("Resource ");
						//			System.out.print(res.getFullPath());
						//			System.out.println(" was added.");
						boolean newModelRegistered = MegamodelRegistrationProcessor.registerModelInFile(megamodel, file);
						if(newModelRegistered){
							try {
								URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
								ResourceSet resourceSet = megamodel.eResource().getResourceSet();
								Resource resource = resourceSet.getResource(uri, true);
								List<IKnowledgeBase> knowledgeBases = IKnowledgeBaseRegistry.INSTANCE.getKnowledgeBases();
								for (IKnowledgeBase knowledgeBase : knowledgeBases) {
									knowledgeBase.generateKnowledge(resource);
								}
								megamodel.eResource().save(Collections.EMPTY_MAP);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						break;
					case IResourceDelta.REMOVED:
						//			System.out.print("Resource ");
						//			System.out.print(res.getFullPath());
						//			System.out.println(" was removed.");
						URI uri = URI.createPlatformResourceURI(res.getFullPath().toString(), true);
						uri = uri.trimFragment();
						List<InstanceModel> instanceModels = new ArrayList<InstanceModel>(megamodel.getInstanceModels());
						for (InstanceModel instanceModel : instanceModels) {
							EObject model = instanceModel.getModel();
							if(model.eIsProxy()){
								megamodel.getModels().remove(instanceModel);
							} else {
								URI uri2 = model.eResource().getURI();
								if(uri.equals(uri2)){
									megamodel.getModels().remove(instanceModel);
								}
							}
							try {
								megamodel.eResource().save(Collections.EMPTY_MAP);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						break;
					case IResourceDelta.CHANGED:
						if(delta.getResource().getType() == IResource.FILE){
							//				System.out.print("Resource ");
							//				System.out.print(delta.getFullPath());
							//				System.out.println(" has changed.");
						}
						int flags = delta.getFlags();
						if ((flags & IResourceDelta.CONTENT) != 0) {
							//				System.out.println("--> Content Change");
						}
						if ((flags & IResourceDelta.REPLACED) != 0) {
							//				System.out.println("--> Content Replaced");
						}
						if ((flags & IResourceDelta.MARKERS) != 0) {
							//				System.out.println("--> Marker Change");
							//					IMarkerDelta[] markers = delta.getMarkerDeltas();
							// if interested in markers, check these deltas
						}
						break;
					}
				}
			}
		}
		return true; // visit the children
	}

	protected static boolean isResourceHidden(IResource resource) {
		// exclude all folders being hidden in the workspace or in the file system
		ResourceAttributes attributes = resource.getResourceAttributes();
		boolean hiddenInWorkspace = resource.isHidden();
		boolean semanticallyHiddenInFilesystem = resource.getName().startsWith(".");
		if(resource instanceof IFolder){
			semanticallyHiddenInFilesystem = semanticallyHiddenInFilesystem || resource.getName().equals("bin");
		}
		boolean hiddenInFilesystem = attributes != null && attributes.isHidden();
		return hiddenInWorkspace || semanticallyHiddenInFilesystem || hiddenInFilesystem;
	}
}