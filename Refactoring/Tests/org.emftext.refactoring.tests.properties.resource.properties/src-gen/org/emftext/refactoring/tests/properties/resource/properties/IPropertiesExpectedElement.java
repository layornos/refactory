/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.refactoring.tests.properties.resource.properties;

/**
 * An element that is expected at a given position in a resource stream.
 */
public interface IPropertiesExpectedElement {
	
	/**
	 * Returns the names of all tokens that are expected at the given position
	 */
	public java.util.Set<String> getTokenNames();
	
	/**
	 * Returns the metaclass of the rule that contains the expected element
	 */
	public org.eclipse.emf.ecore.EClass getRuleMetaclass();
	
	/**
	 * Adds an element that is a valid follower for this element
	 */
	public void addFollower(org.emftext.refactoring.tests.properties.resource.properties.IPropertiesExpectedElement follower, org.eclipse.emf.ecore.EStructuralFeature[] path);
	
	/**
	 * Returns all valid followers for this element
	 */
	public java.util.Collection<org.emftext.refactoring.tests.properties.resource.properties.util.PropertiesPair<org.emftext.refactoring.tests.properties.resource.properties.IPropertiesExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>> getFollowers();
	
}
