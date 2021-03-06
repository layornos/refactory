/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.refactoring.tests.properties.resource.testproperties.mopp;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

public class TestpropertiesReferenceResolverSwitch implements org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolverSwitch {
	
	/**
	 * This map stores a copy of the options the were set for loading the resource.
	 */
	private Map<Object, Object> options;
	
	protected org.emftext.refactoring.tests.properties.resource.testproperties.analysis.EObjectReferenceValueObjectReferenceReferenceResolver eObjectReferenceValueObjectReferenceReferenceResolver = new org.emftext.refactoring.tests.properties.resource.testproperties.analysis.EObjectReferenceValueObjectReferenceReferenceResolver();
	
	public org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver<org.emftext.refactoring.tests.properties.EObjectReferenceValue, org.eclipse.emf.ecore.EObject> getEObjectReferenceValueObjectReferenceReferenceResolver() {
		return getResolverChain(org.emftext.refactoring.tests.properties.PropertiesPackage.eINSTANCE.getEObjectReferenceValue_ObjectReference(), eObjectReferenceValueObjectReferenceReferenceResolver);
	}
	
	public void setOptions(Map<?, ?> options) {
		if (options != null) {
			this.options = new LinkedHashMap<Object, Object>();
			this.options.putAll(options);
		}
		eObjectReferenceValueObjectReferenceReferenceResolver.setOptions(options);
	}
	
	public void resolveFuzzy(String identifier, EObject container, EReference reference, int position, org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolveResult<EObject> result) {
		if (container == null) {
			return;
		}
		if (org.emftext.refactoring.tests.properties.PropertiesPackage.eINSTANCE.getEObjectReferenceValue().isInstance(container)) {
			TestpropertiesFuzzyResolveResult<org.eclipse.emf.ecore.EObject> frr = new TestpropertiesFuzzyResolveResult<org.eclipse.emf.ecore.EObject>(result);
			String referenceName = reference.getName();
			EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof EReference && referenceName != null && referenceName.equals("objectReference")) {
				eObjectReferenceValueObjectReferenceReferenceResolver.resolve(identifier, (org.emftext.refactoring.tests.properties.EObjectReferenceValue) container, (EReference) feature, position, true, frr);
			}
		}
	}
	
	public org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver<? extends EObject, ? extends EObject> getResolver(EStructuralFeature reference) {
		if (reference == org.emftext.refactoring.tests.properties.PropertiesPackage.eINSTANCE.getEObjectReferenceValue_ObjectReference()) {
			return getResolverChain(reference, eObjectReferenceValueObjectReferenceReferenceResolver);
		}
		return null;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public <ContainerType extends EObject, ReferenceType extends EObject> org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver<ContainerType, ReferenceType> getResolverChain(EStructuralFeature reference, org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver<ContainerType, ReferenceType> originalResolver) {
		if (options == null) {
			return originalResolver;
		}
		Object value = options.get(org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesOptions.ADDITIONAL_REFERENCE_RESOLVERS);
		if (value == null) {
			return originalResolver;
		}
		if (!(value instanceof Map)) {
			// send this to the error log
			new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil().logWarning("Found value with invalid type for option " + org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + Map.class.getName() + ", but was " + value.getClass().getName() + ")", null);
			return originalResolver;
		}
		Map<?,?> resolverMap = (Map<?,?>) value;
		Object resolverValue = resolverMap.get(reference);
		if (resolverValue == null) {
			return originalResolver;
		}
		if (resolverValue instanceof org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver) {
			org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver replacingResolver = (org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver) resolverValue;
			if (replacingResolver instanceof org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesDelegatingReferenceResolver) {
				// pass original resolver to the replacing one
				((org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesDelegatingReferenceResolver) replacingResolver).setDelegate(originalResolver);
			}
			return replacingResolver;
		} else if (resolverValue instanceof Collection) {
			Collection replacingResolvers = (Collection) resolverValue;
			org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver replacingResolver = originalResolver;
			for (Object next : replacingResolvers) {
				if (next instanceof org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceCache) {
					org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver nextResolver = (org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesReferenceResolver) next;
					if (nextResolver instanceof org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesDelegatingReferenceResolver) {
						// pass original resolver to the replacing one
						((org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesDelegatingReferenceResolver) nextResolver).setDelegate(replacingResolver);
					}
					replacingResolver = nextResolver;
				} else {
					// The collection contains a non-resolver. Send a warning to the error log.
					new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil().logWarning("Found value with invalid type in value map for option " + org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesDelegatingReferenceResolver.class.getName() + ", but was " + next.getClass().getName() + ")", null);
				}
			}
			return replacingResolver;
		} else {
			// The value for the option ADDITIONAL_REFERENCE_RESOLVERS has an unknown type.
			new org.emftext.refactoring.tests.properties.resource.testproperties.util.TestpropertiesRuntimeUtil().logWarning("Found value with invalid type in value map for option " + org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesDelegatingReferenceResolver.class.getName() + ", but was " + resolverValue.getClass().getName() + ")", null);
			return originalResolver;
		}
	}
	
}
