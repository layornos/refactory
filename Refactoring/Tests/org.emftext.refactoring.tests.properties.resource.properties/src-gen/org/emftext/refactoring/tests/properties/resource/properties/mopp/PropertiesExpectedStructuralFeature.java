/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.refactoring.tests.properties.resource.properties.mopp;

/**
 * A representation for a range in a document where a structural feature (e.g., a
 * reference) is expected.
 */
public class PropertiesExpectedStructuralFeature extends org.emftext.refactoring.tests.properties.resource.properties.mopp.PropertiesAbstractExpectedElement {
	
	private org.emftext.refactoring.tests.properties.resource.properties.grammar.PropertiesPlaceholder placeholder;
	
	public PropertiesExpectedStructuralFeature(org.emftext.refactoring.tests.properties.resource.properties.grammar.PropertiesPlaceholder placeholder) {
		super(placeholder.getMetaclass());
		this.placeholder = placeholder;
	}
	
	public org.eclipse.emf.ecore.EStructuralFeature getFeature() {
		return placeholder.getFeature();
	}
	
	public String getTokenName() {
		return placeholder.getTokenName();
	}
	
	public java.lang.String toString() {
		return "EFeature " + getFeature().getEContainingClass().getName() + "." + getFeature().getName();
	}
	
	public boolean equals(java.lang.Object o) {
		if (o instanceof PropertiesExpectedStructuralFeature) {
			return getFeature().equals(((PropertiesExpectedStructuralFeature) o).getFeature());
		}
		return false;
	}
}