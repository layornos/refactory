/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.refactoring.refactoring_specification.resource.refspec.analysis;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.refactoring.refactoring_specification.RefactoringSpecification;
import org.emftext.language.refactoring.roles.Role;
import org.emftext.language.refactoring.roles.RoleModel;

public class RoleReferenceRoleReferenceResolver implements org.emftext.language.refactoring.refactoring_specification.resource.refspec.IRefspecReferenceResolver<org.emftext.language.refactoring.refactoring_specification.RoleReference, org.emftext.language.refactoring.roles.Role> {
	
	private org.emftext.language.refactoring.refactoring_specification.resource.refspec.analysis.RefspecDefaultResolverDelegate<org.emftext.language.refactoring.refactoring_specification.RoleReference, org.emftext.language.refactoring.roles.Role> delegate = new org.emftext.language.refactoring.refactoring_specification.resource.refspec.analysis.RefspecDefaultResolverDelegate<org.emftext.language.refactoring.refactoring_specification.RoleReference, org.emftext.language.refactoring.roles.Role>();
	
	public void resolve(java.lang.String identifier, org.emftext.language.refactoring.refactoring_specification.RoleReference container, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, final org.emftext.language.refactoring.refactoring_specification.resource.refspec.IRefspecReferenceResolveResult<org.emftext.language.refactoring.roles.Role> result) {
		EObject parent = container.eContainer().eContainer();
		if(!(parent instanceof RefactoringSpecification)){
			result.setErrorMessage("Not a valid Refactoring Specification");
			return;
		}
		RefactoringSpecification specification = (RefactoringSpecification) parent;
		RoleModel roleModel = specification.getUsedRoleModel();
		EList<Role> roles = roleModel.getRoles();
		for (Role role : roles) {
			if(role.getName().equals(identifier)){
				result.addMapping(identifier, role);
				return;
			}
		}
		result.setErrorMessage("Role '" + identifier + "' isn't declared in the specified RoleModel");
	}
	
	public java.lang.String deResolve(org.emftext.language.refactoring.roles.Role element, org.emftext.language.refactoring.refactoring_specification.RoleReference container, org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		// save options in a field or leave method empty if this resolver does not depend on any option
	}
	
}
