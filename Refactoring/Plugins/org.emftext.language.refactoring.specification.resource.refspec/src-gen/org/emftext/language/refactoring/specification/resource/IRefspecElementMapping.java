/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.refactoring.specification.resource;

// A mapping from an identifier to an EObject.
//
// @param <ReferenceType> the type of the reference this mapping points to.
public interface IRefspecElementMapping<ReferenceType> extends org.emftext.language.refactoring.specification.resource.IRefspecReferenceMapping<ReferenceType> {
	
	// Returns the target object the identifier is mapped to.
	public ReferenceType getTargetElement();
}
