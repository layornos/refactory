/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.refactoring.specification.resource.analysis;

public class RefspecLOWER_IDENTIFIERTokenResolver implements org.emftext.language.refactoring.specification.resource.IRefspecTokenResolver {
	
	private org.emftext.language.refactoring.specification.resource.analysis.RefspecDefaultTokenResolver defaultTokenResolver = new org.emftext.language.refactoring.specification.resource.analysis.RefspecDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		java.lang.String result = defaultTokenResolver.deResolve(value, feature, container);
		return result;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, org.emftext.language.refactoring.specification.resource.IRefspecTokenResolveResult result) {
		defaultTokenResolver.resolve(lexem, feature, result);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
