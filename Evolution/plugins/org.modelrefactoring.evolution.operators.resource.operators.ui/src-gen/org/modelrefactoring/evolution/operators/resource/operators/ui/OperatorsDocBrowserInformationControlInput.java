/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.modelrefactoring.evolution.operators.resource.operators.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Provides input for the <code>TextHover</code>. The most is copied from
 * <code>org.eclipse.jdt.internal.ui.text.java.hover.JavadocBrowserInformationContr
 * olInput</code>.
 */
public class OperatorsDocBrowserInformationControlInput {
	
	private final OperatorsDocBrowserInformationControlInput fPrevious;
	private OperatorsDocBrowserInformationControlInput fNext;
	private final EObject element;
	private final String htmlContent;
	private final String tokenText;
	private final Resource resource;
	
	/**
	 * Creates a new browser information control input.
	 * 
	 * @param previous previous input, or <code>null</code> if none available
	 * @param element the element, or <code>null</code> if none available
	 * @param htmlContent HTML contents, must not be null
	 */
	public OperatorsDocBrowserInformationControlInput(OperatorsDocBrowserInformationControlInput previous, EObject element, Resource resource, String htmlContent, String tokenText) {
		fPrevious= previous;
		if (previous != null) {
			previous.fNext= this;
		}
		assert htmlContent != null;
		this.element = element;
		this.htmlContent = htmlContent;
		this.tokenText = tokenText;
		this.resource = resource;
	}
	
	/**
	 * Returns the previous input or <code>null</code> if this is the first.
	 * 
	 * @return the previous input or <code>null</code>
	 */
	public OperatorsDocBrowserInformationControlInput getPrevious() {
		return fPrevious;
	}
	
	/**
	 * Returns the next input or <code>null</code> if this is the last.
	 * 
	 * @return the next input or <code>null</code>
	 */
	public OperatorsDocBrowserInformationControlInput getNext() {
		return fNext;
	}
	
	/**
	 * 
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}
	
	public String getHtml() {
		return htmlContent;
	}
	
	public String toString() {
		return getHtml();
	}
	
	/**
	 * 
	 * @return the token text, it is needed for a hyperlink where the caret has to
	 * jump to
	 */
	public String getTokenText() {
		return tokenText;
	}
	
	public Object getInputElement() {
		return element == null ? (Object) htmlContent : element;
	}
	
	public String getInputName() {
		return element == null ? "" : element.toString();
	}
	
	public int getLeadingImageWidth() {
		return 0;
	}
}
