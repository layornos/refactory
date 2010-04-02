/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.refactoring.specification.resource.mopp;

public class RefspecSyntaxElementDecorator {
	
	// the syntax element to be decorated
	private org.emftext.language.refactoring.specification.resource.grammar.RefspecSyntaxElement decoratedElement;
	
	// an array of child decorators (one decorator per child of the decorated syntax element
	private RefspecSyntaxElementDecorator[] childDecorators;
	
	// a list of the indices that must be printed
	private java.util.List<java.lang.Integer> indicesToPrint = new java.util.ArrayList<java.lang.Integer>();
	
	public RefspecSyntaxElementDecorator(org.emftext.language.refactoring.specification.resource.grammar.RefspecSyntaxElement decoratedElement, RefspecSyntaxElementDecorator[] childDecorators) {
		super();
		this.decoratedElement = decoratedElement;
		this.childDecorators = childDecorators;
	}
	
	public void addIndexToPrint(java.lang.Integer index) {
		indicesToPrint.add(index);
	}
	
	public org.emftext.language.refactoring.specification.resource.grammar.RefspecSyntaxElement getDecoratedElement() {
		return decoratedElement;
	}
	
	public RefspecSyntaxElementDecorator[] getChildDecorators() {
		return childDecorators;
	}
	
	public java.lang.Integer getNextIndexToPrint() {
		if (indicesToPrint.size() == 0) {
			return null;
		}
		return indicesToPrint.remove(0);
	}
	
}
