/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package dk.itu.sdg.language.xwpf.resource.xwpf.mopp;

/**
 * A representation for a range in a document where a boolean attribute is
 * expected.
 */
public class XwpfExpectedBooleanTerminal extends dk.itu.sdg.language.xwpf.resource.xwpf.mopp.XwpfAbstractExpectedElement {
	
	private dk.itu.sdg.language.xwpf.resource.xwpf.grammar.XwpfBooleanTerminal booleanTerminal;
	
	public XwpfExpectedBooleanTerminal(dk.itu.sdg.language.xwpf.resource.xwpf.grammar.XwpfBooleanTerminal booleanTerminal) {
		super(booleanTerminal.getMetaclass());
		this.booleanTerminal = booleanTerminal;
	}
	
	public dk.itu.sdg.language.xwpf.resource.xwpf.grammar.XwpfBooleanTerminal getBooleanTerminal() {
		return booleanTerminal;
	}
	
	/**
	 * Returns the expected boolean terminal.
	 */
	public dk.itu.sdg.language.xwpf.resource.xwpf.grammar.XwpfSyntaxElement getSymtaxElement() {
		return booleanTerminal;
	}
	
	private org.eclipse.emf.ecore.EStructuralFeature getFeature() {
		return booleanTerminal.getFeature();
	}
	
	public String toString() {
		return "EFeature " + getFeature().getEContainingClass().getName() + "." + getFeature().getName();
	}
	
	public boolean equals(Object o) {
		if (o instanceof XwpfExpectedBooleanTerminal) {
			return getFeature().equals(((XwpfExpectedBooleanTerminal) o).getFeature());
		}
		return false;
	}
	
	@Override	
	public int hashCode() {
		return getFeature().hashCode();
	}
	
	public java.util.Set<String> getTokenNames() {
		// BooleanTerminals are associated with two or one token(s)
		java.util.Set<String> tokenNames = new java.util.LinkedHashSet<String>(2);
		String trueLiteral = booleanTerminal.getTrueLiteral();
		if (!"".equals(trueLiteral)) {
			tokenNames.add("'" + trueLiteral + "'");
		}
		String falseLiteral = booleanTerminal.getFalseLiteral();
		if (!"".equals(falseLiteral)) {
			tokenNames.add("'" + falseLiteral + "'");
		}
		return tokenNames;
	}
	
}
