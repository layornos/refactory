/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package dk.itu.sdg.language.xwpf.resource.xwpf;

public enum XwpfEProblemType {
	SYNTAX_ERROR, PRINT_PROBLEM, UNRESOLVED_REFERENCE, ANALYSIS_PROBLEM, BUILDER_ERROR, UNKNOWN, ;
	
	/**
	 * Returns the ID that is used for this type of problem when creating markers for
	 * problems.
	 */
	public String getID() {
		if (this == UNKNOWN) {
			return "";
		}
		return this.name().toLowerCase();
	}
	
}
