/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.modelrefactoring.guery.resource.guery.ui;

/**
 * This class can be used to initialize default preference values.
 */
public class GueryPreferenceInitializer extends org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer {
	
	public void initializeDefaultPreferences() {
		
		initializeDefaultSyntaxHighlighting();
		initializeDefaultBrackets();
		
		org.eclipse.jface.preference.IPreferenceStore store = org.modelrefactoring.guery.resource.guery.ui.GueryUIPlugin.getDefault().getPreferenceStore();
		// Set default value for matching brackets
		store.setDefault(org.modelrefactoring.guery.resource.guery.ui.GueryPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR, "192,192,192");
		store.setDefault(org.modelrefactoring.guery.resource.guery.ui.GueryPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX, true);
		
	}
	
	protected void initializeDefaultBrackets() {
		org.eclipse.jface.preference.IPreferenceStore store = org.modelrefactoring.guery.resource.guery.ui.GueryUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultBrackets(store, new org.modelrefactoring.guery.resource.guery.mopp.GueryMetaInformation());
	}
	
	protected void initializeDefaultBrackets(org.eclipse.jface.preference.IPreferenceStore store, org.modelrefactoring.guery.resource.guery.IGueryMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		// set default brackets
		org.modelrefactoring.guery.resource.guery.ui.GueryBracketSet bracketSet = new org.modelrefactoring.guery.resource.guery.ui.GueryBracketSet();
		final java.util.Collection<org.modelrefactoring.guery.resource.guery.IGueryBracketPair> bracketPairs = metaInformation.getBracketPairs();
		if (bracketPairs != null) {
			for (org.modelrefactoring.guery.resource.guery.IGueryBracketPair bracketPair : bracketPairs) {
				bracketSet.addBracketPair(bracketPair.getOpeningBracket(), bracketPair.getClosingBracket(), bracketPair.isClosingEnabledInside(), bracketPair.isCloseAfterEnter());
			}
		}
		store.setDefault(languageId + org.modelrefactoring.guery.resource.guery.ui.GueryPreferenceConstants.EDITOR_BRACKETS_SUFFIX, bracketSet.serialize());
	}
	
	public void initializeDefaultSyntaxHighlighting() {
		org.eclipse.jface.preference.IPreferenceStore store = org.modelrefactoring.guery.resource.guery.ui.GueryUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultSyntaxHighlighting(store, new org.modelrefactoring.guery.resource.guery.mopp.GueryMetaInformation());
	}
	
	protected void initializeDefaultSyntaxHighlighting(org.eclipse.jface.preference.IPreferenceStore store, org.modelrefactoring.guery.resource.guery.mopp.GueryMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		String[] tokenNames = metaInformation.getSyntaxHighlightableTokenNames();
		if (tokenNames == null) {
			return;
		}
		for (int i = 0; i < tokenNames.length; i++) {
			String tokenName = tokenNames[i];
			org.modelrefactoring.guery.resource.guery.IGueryTokenStyle style = metaInformation.getDefaultTokenStyle(tokenName);
			if (style != null) {
				String color = getColorString(style.getColorAsRGB());
				setProperties(store, languageId, tokenName, color, style.isBold(), true, style.isItalic(), style.isStrikethrough(), style.isUnderline());
			} else {
				setProperties(store, languageId, tokenName, "0,0,0", false, false, false, false, false);
			}
		}
	}
	
	protected void setProperties(org.eclipse.jface.preference.IPreferenceStore store, String languageID, String tokenName, String color, boolean bold, boolean enable, boolean italic, boolean strikethrough, boolean underline) {
		store.setDefault(org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.StyleProperty.BOLD), bold);
		store.setDefault(org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.StyleProperty.COLOR), color);
		store.setDefault(org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.StyleProperty.ENABLE), enable);
		store.setDefault(org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.StyleProperty.ITALIC), italic);
		store.setDefault(org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.StyleProperty.STRIKETHROUGH), strikethrough);
		store.setDefault(org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.modelrefactoring.guery.resource.guery.ui.GuerySyntaxColoringHelper.StyleProperty.UNDERLINE), underline);
	}
	
	protected String getColorString(int[] colorAsRGB) {
		if (colorAsRGB == null) {
			return "0,0,0";
		}
		if (colorAsRGB.length != 3) {
			return "0,0,0";
		}
		return colorAsRGB[0] + "," +colorAsRGB[1] + ","+ colorAsRGB[2];
	}
	
}

