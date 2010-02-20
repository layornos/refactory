/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.refactoring.refactoring_specification.resource.refspec;

// A token that is returned by ITextLexer instances. Each token
// has a name, a length and is found at a certain offset in a
// document.
public interface IRefspecTextToken {
	
	// Returns the name of this token.
	public String getName();
	
	// Returns the offset at which the token was found in the document.
	public int getOffset();
	
	// Returns the length of this token.
	public int getLength();
	
	// Returns false if the token is not usable for syntax highlighting.
	public boolean canBeUsedForSyntaxHighlighting();
	
	public String getText();
}
