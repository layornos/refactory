/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.modelrefactoring.evolution.operators.resource.operators.analysis;

import org.eclipse.emf.ecore.EReference;

public class ASSIGNAttributeReferenceResolver implements org.modelrefactoring.evolution.operators.resource.operators.IOperatorsReferenceResolver<org.modelrefactoring.evolution.operators.ASSIGN, org.eclipse.emf.ecore.EAttribute> {
	
	private org.modelrefactoring.evolution.operators.resource.operators.analysis.OperatorsDefaultResolverDelegate<org.modelrefactoring.evolution.operators.ASSIGN, org.eclipse.emf.ecore.EAttribute> delegate = new org.modelrefactoring.evolution.operators.resource.operators.analysis.OperatorsDefaultResolverDelegate<org.modelrefactoring.evolution.operators.ASSIGN, org.eclipse.emf.ecore.EAttribute>();
	
	public void resolve(String identifier, org.modelrefactoring.evolution.operators.ASSIGN container, EReference reference, int position, boolean resolveFuzzy, final org.modelrefactoring.evolution.operators.resource.operators.IOperatorsReferenceResolveResult<org.eclipse.emf.ecore.EAttribute> result) {
		delegate.resolve(identifier, container, reference, position, resolveFuzzy, result);
	}
	
	public String deResolve(org.eclipse.emf.ecore.EAttribute element, org.modelrefactoring.evolution.operators.ASSIGN container, EReference reference) {
		return delegate.deResolve(element, container, reference);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		// save options in a field or leave method empty if this resolver does not depend
		// on any option
	}
	
}
