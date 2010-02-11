/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.refactoring.roles.resource.rolestext;

// This interface provides information about a generated EMFText
// text resource plug-in.
public interface IRolestextMetaInformation {
	
	public String getURI();
	
	// Returns the name of the concrete syntax. This name is used
	// as file extension.
	//
	// @return
	public String getSyntaxName();
	
	// Returns the relative path to the .cs file within the plug-in.
	// @return
	public String getPathToCSDefinition();
	
	// Return a lexer capable to split the underlying text file into tokens.
	//
	// @return a lexer instance.
	public org.emftext.language.refactoring.roles.resource.rolestext.IRolestextTextScanner createLexer();
	
	// Returns an instance of the parser. This factory method
	// is needed, because we can not create ANTLR parsers using
	// the default constructor without arguments, because they
	// expect the input stream or rather a token stream.
	//
	// @param inputStream
	// @param encoding
	// @return
	public org.emftext.language.refactoring.roles.resource.rolestext.IRolestextTextParser createParser(java.io.InputStream inputStream, String encoding);
	
	// Returns all meta classes for which syntax was defined. This
	// information is used both by the NewFileWizard and the code
	// completion.
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax();
	
	// Returns an instance of the reference resolver switch class.
	public org.emftext.language.refactoring.roles.resource.rolestext.IRolestextReferenceResolverSwitch getReferenceResolverSwitch();
	
	// Returns an instance of the token resolver factory.
	public org.emftext.language.refactoring.roles.resource.rolestext.IRolestextTokenResolverFactory getTokenResolverFactory();
	
	// Returns a list of all tokens defined in the syntax.
	//
	// @return
	public String[] getTokenNames();
	
	// Return the default style that should be used to present tokens of the
	// given type.
	//
	// @param tokenName the name of the token type
	// @return a style object or null if not default style is set
	public org.emftext.language.refactoring.roles.resource.rolestext.IRolestextTokenStyle getDefaultTokenStyle(String tokenName);
	
	// Returns the default bracket pairs.
	//
	// @return
	public java.util.Collection<org.emftext.language.refactoring.roles.resource.rolestext.IRolestextBracketPair> getBracketPairs();
	
	// Returns all classes for which folding should be enabled
	// in the editor.
	public org.eclipse.emf.ecore.EClass[] getFoldableClasses();
	
	// @return a hover text provider which provides the hover text of an <code>EObject</code>
	public org.emftext.language.refactoring.roles.resource.rolestext.IRolestextHoverTextProvider getHoverTextProvider();
}
