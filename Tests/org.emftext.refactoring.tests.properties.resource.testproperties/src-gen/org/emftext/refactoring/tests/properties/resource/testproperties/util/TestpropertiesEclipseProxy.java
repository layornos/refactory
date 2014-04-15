/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.refactoring.tests.properties.resource.testproperties.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ILiveValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

/**
 * A utility class that bundles all dependencies to the Eclipse platform. Clients
 * of this class must check whether the Eclipse bundles are available in the
 * classpath. If they are not available, this class is not used, which allows to
 * run resource plug-in that are generated by EMFText in stand-alone mode. In this
 * case the EMF JARs are sufficient to parse and print resources.
 */
public class TestpropertiesEclipseProxy {
	
	/**
	 * Adds all registered load option provider extension to the given map. Load
	 * option providers can be used to set default options for loading resources (e.g.
	 * input stream pre-processors).
	 */
	public void getDefaultLoadOptionProviderExtensions(Map<Object, Object> optionsMap) {
		if (Platform.isRunning()) {
			// find default load option providers
			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
			IConfigurationElement configurationElements[] = extensionRegistry.getConfigurationElementsFor(org.emftext.refactoring.tests.properties.resource.testproperties.mopp.TestpropertiesPlugin.EP_DEFAULT_LOAD_OPTIONS_ID);
			for (IConfigurationElement element : configurationElements) {
				try {
					org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesOptionProvider provider = (org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesOptionProvider) element.createExecutableExtension("class");
					final Map<?, ?> options = provider.getOptions();
					final Collection<?> keys = options.keySet();
					for (Object key : keys) {
						org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesMapUtil.putAndMergeKeys(optionsMap, key, options.get(key));
					}
				} catch (CoreException ce) {
					new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil().logError("Exception while getting default options.", ce);
				}
			}
		}
	}
	
	/**
	 * Adds all registered resource factory extensions to the given map. Such
	 * extensions can be used to register multiple resource factories for the same
	 * file extension.
	 */
	public void getResourceFactoryExtensions(Map<String, Factory> factories) {
		if (Platform.isRunning()) {
			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
			IConfigurationElement configurationElements[] = extensionRegistry.getConfigurationElementsFor(org.emftext.refactoring.tests.properties.resource.testproperties.mopp.TestpropertiesPlugin.EP_ADDITIONAL_EXTENSION_PARSER_ID);
			for (IConfigurationElement element : configurationElements) {
				try {
					String type = element.getAttribute("type");
					Resource.Factory factory = (Resource.Factory) element.createExecutableExtension("class");
					if (type == null) {
						type = "";
					}
					Resource.Factory otherFactory = factories.get(type);
					if (otherFactory != null) {
						Class<?> superClass = factory.getClass().getSuperclass();
						while(superClass != Object.class) {
							if (superClass.equals(otherFactory.getClass())) {
								factories.put(type, factory);
								break;
							}
							superClass = superClass.getClass();
						}
					}
					else {
						factories.put(type, factory);
					}
				} catch (CoreException ce) {
					new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil().logError("Exception while getting default options.", ce);
				}
			}
		}
	}
	
	/**
	 * Gets the resource that is contained in the give file.
	 */
	public org.emftext.refactoring.tests.properties.resource.testproperties.mopp.TestpropertiesResource getResource(IFile file) {
		ResourceSet rs = new ResourceSetImpl();
		Resource resource = rs.getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true);
		return (org.emftext.refactoring.tests.properties.resource.testproperties.mopp.TestpropertiesResource) resource;
	}
	
	/**
	 * Returns the file that contains the given resource.
	 */
	public IFile getFileForResource(Resource resource) {
		return getFileForURI(resource.getURI());
	}
	
	/**
	 * Returns the file that corresponds to the given URI. If the URI does not
	 * correspond to a file (e.g., because it is not a platform URI or because it is
	 * <code>null</code>), <code>null</code> is returned.
	 */
	public IFile getFileForURI(URI uri) {
		if (uri == null) {
			return null;
		}
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		String platformString = uri.toPlatformString(true);
		// If the URI is not a platform URI, we cannot determine the file.
		if (platformString == null) {
			return null;
		}
		Path path = new Path(platformString);
		return workspaceRoot.getFile(path);
	}
	
	/**
	 * Returns the encoding for this resource that is specified in the workspace file
	 * properties or determined by the default workspace encoding in Eclipse.
	 */
	public String getPlatformResourceEncoding(URI uri) {
		// We can't determine the encoding if the platform is not running.
		if (!new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil().isEclipsePlatformRunning()) {
			return null;
		}
		if (uri != null && uri.isPlatform()) {
			String platformString = uri.toPlatformString(true);
			IResource platformResource = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
			if (platformResource instanceof IFile) {
				IFile file = (IFile) platformResource;
				try {
					return file.getCharset();
				} catch (CoreException ce) {
					new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil().logWarning("Could not determine encoding of platform resource: " + uri.toString(), ce);
				}
			}
		}
		return null;
	}
	
	/**
	 * Checks all registered EMF validation constraints. Note: EMF validation does not
	 * work if OSGi is not running.
	 */
	@SuppressWarnings("restriction")
	public void checkEMFValidationConstraints(org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesTextResource resource, EObject root, boolean includeBatchConstraints) {
		// The EMF validation framework code throws a NPE if the validation plug-in is not
		// loaded. This is a bug, which is fixed in the Helios release. Nonetheless, we
		// need to catch the exception here.
		org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil runtimeUtil = new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil();
		if (runtimeUtil.isEclipsePlatformRunning() && runtimeUtil.isEMFValidationAvailable()) {
			// The EMF validation framework code throws a NPE if the validation plug-in is not
			// loaded. This is a workaround for bug 322079.
			if (org.eclipse.emf.validation.internal.EMFModelValidationPlugin.getPlugin() != null) {
				try {
					ModelValidationService service = ModelValidationService.getInstance();
					IStatus status;
					// Batch constraints are only evaluated if requested (e.g., when a resource is
					// loaded for the first time).
					if (includeBatchConstraints) {
						IBatchValidator validator = service.<EObject, IBatchValidator>newValidator(EvaluationMode.BATCH);
						validator.setIncludeLiveConstraints(false);
						status = validator.validate(root);
						addStatus(status, resource, root, org.emftext.refactoring.tests.properties.resource.testproperties.TestpropertiesEProblemType.BATCH_CONSTRAINT_PROBLEM);
					}
					// Live constraints are always evaluated
					ILiveValidator validator = service.<Notification, ILiveValidator>newValidator(EvaluationMode.LIVE);
					Collection<Notification> notifications = createNotifications(root);
					status = validator.validate(notifications);
					addStatus(status, resource, root, org.emftext.refactoring.tests.properties.resource.testproperties.TestpropertiesEProblemType.LIVE_CONSTRAINT_PROBLEM);
				} catch (Throwable t) {
					new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil().logError("Exception while checking contraints provided by EMF validator classes.", t);
				}
			}
		}
	}
	
	private Collection<Notification> createNotifications(EObject eObject) {
		List<Notification> notifications = new ArrayList<Notification>();
		createNotification(eObject, notifications);
		Iterator<EObject> allContents = eObject.eAllContents();
		while (allContents.hasNext()) {
			EObject next = (EObject) allContents.next();
			createNotification(next, notifications);
		}
		return notifications;
	}
	
	private void createNotification(EObject eObject, List<Notification> notifications) {
		if (eObject instanceof InternalEObject) {
			InternalEObject internalEObject = (InternalEObject) eObject;
			Notification notification = new ENotificationImpl(internalEObject, 0, ENotificationImpl.NO_FEATURE_ID, null, null);
			notifications.add(notification);
		}
	}
	
	public void addStatus(IStatus status, org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesTextResource resource, EObject root, org.emftext.refactoring.tests.properties.resource.testproperties.TestpropertiesEProblemType problemType) {
		List<EObject> causes = new ArrayList<EObject>();
		causes.add(root);
		if (status instanceof ConstraintStatus) {
			ConstraintStatus constraintStatus = (ConstraintStatus) status;
			Set<EObject> resultLocus = constraintStatus.getResultLocus();
			causes.clear();
			causes.addAll(resultLocus);
		}
		IStatus[] children = status.getChildren();
		boolean hasChildren = children != null && children.length > 0;
		// Ignore composite status objects that have children. The actual status
		// information is then contained in the child objects.
		if (!status.isMultiStatus() || !hasChildren) {
			int severity = status.getSeverity();
			if (severity == IStatus.ERROR) {
				for (EObject cause : causes) {
					resource.addError(status.getMessage(), problemType, cause);
				}
			}
			if (severity == IStatus.WARNING) {
				for (EObject cause : causes) {
					resource.addWarning(status.getMessage(), problemType, cause);
				}
			}
		}
		if (children != null) {
			for (IStatus child : children) {
				addStatus(child, resource, root, problemType);
			}
		}
	}
	
}
