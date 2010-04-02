/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.refactoring.specification.resource;

// Implementors of this interface can provide a post-processor
// for text resources.
public interface IRefspecResourcePostProcessorProvider {
	
	// Returns the processor that shall be called after text
	// resource are successfully parsed.
	public org.emftext.language.refactoring.specification.resource.IRefspecResourcePostProcessor getResourcePostProcessor();
}
