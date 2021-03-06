/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.modelrefactoring.evolution.coed.resource.coed.ui;

import java.util.ArrayList;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.custom.StyledText;

/**
 * A container for all bracket pairs.
 */
public class CoedBracketSet {
	
	/**
	 * The separator between a bracket pair, must not contain characters that need to
	 * be escaped as it will be used as regular expression.
	 */
	public final static String BRACKET_SEPARATOR = " and ";
	
	private final static String SERIAL_SEPARATOR = "#";
	
	private final static org.modelrefactoring.evolution.coed.resource.coed.ui.CoedPositionHelper positionHelper = new org.modelrefactoring.evolution.coed.resource.coed.ui.CoedPositionHelper();
	
	private ArrayList<org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair> bracketPairs;
	private String languageID;
	
	/**
	 * Creates a new bracket set to manage bracket pairs.
	 */
	public CoedBracketSet() {
		super();
		this.languageID = new org.modelrefactoring.evolution.coed.resource.coed.mopp.CoedMetaInformation().getSyntaxName();
		this.bracketPairs = new ArrayList<org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair>();
	}
	
	/**
	 * Checks whether the given string is an opening bracket.
	 */
	public boolean isOpeningBracket(String bracket) {
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			if (bracket.equals(bracketPair.getOpeningBracket())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks whether the given string is a bracket (opening or closing).
	 */
	public boolean isBracket(String bracket) {
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			if (bracket.equals(bracketPair.getOpeningBracket()) || bracket.equals(bracketPair.getClosingBracket())) {
				return true;
			}
		}
		return false;
	}
	
	public org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair getBracketPair(int index) {
		try {
			return bracketPairs.get(index);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Returns the bracket pair with the given opening and closing bracket. If no
	 * matching pair is found, <code>null</code> is returned.
	 */
	public org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair getBracketPair(String opening, String closing) {
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			if (bracketPair.getOpeningBracket().equals(opening) && bracketPair.getClosingBracket().equals(closing)) {
				return bracketPair;
			}
		}
		return null;
	}
	
	/**
	 * Adds a new bracket pair to this bracket set.
	 */
	public boolean addBracketPair(String opening, String closing, boolean closingEnabledInside, boolean closeAfterEnter) {
		if (isBracket(opening) || isBracket(closing)) {
			return false;
		}
		bracketPairs.add(new org.modelrefactoring.evolution.coed.resource.coed.mopp.CoedBracketPair(opening, closing, closingEnabledInside, closeAfterEnter));
		return true;
	}
	
	/**
	 * Removes all bracket pairs from this bracket set and reloads the bracket set
	 * from the preference store.
	 */
	public boolean resetBrackets(IPreferenceStore preferenceStore) {
		String bracketPairs = preferenceStore.getString(languageID + org.modelrefactoring.evolution.coed.resource.coed.ui.CoedPreferenceConstants.EDITOR_BRACKETS_SUFFIX);
		if (bracketPairs == null) {
			return false;
		}
		deserialize(bracketPairs);
		return true;
	}
	
	/**
	 * Returns the counter part of a bracket (i.e., the closing bracket for the
	 * opening one and the other way around). If no respective bracket is found,
	 * <code>null</code> is returned.
	 */
	public String getCounterpart(String bracket) {
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			if (bracket.equals(bracketPair.getOpeningBracket())) {
				return bracketPair.getClosingBracket();
			}
			if (bracket.equals(bracketPair.getClosingBracket())) {
				return bracketPair.getOpeningBracket();
			}
		}
		return null;
	}
	
	public int size() {
		return bracketPairs.size();
	}
	
	/**
	 * Removes the given bracket pair.
	 */
	public org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair remove(String opening, String closing) {
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			if (bracketPair.getOpeningBracket().equals(opening) && bracketPair.getClosingBracket().equals(closing)) {
				bracketPairs.remove(bracketPair);
				return bracketPair;
			}
		}
		return null;
	}
	
	/**
	 * Removes pairs of brackets.
	 */
	public void removeBracketPairs(String[] bracketsAsArray) {
		for (String bracket : bracketsAsArray) {
			String[] tmp = bracket.split(BRACKET_SEPARATOR);
			remove(tmp[0], tmp[1]);
		}
	}
	
	/**
	 * Removes the old bracket set and sets the given bracket set. It is useful to
	 * take a stored <code>String</code> in a preference store. A bracket pair
	 * contains of opening, closing and the flags 'closingEnabledInside' and
	 * 'closeAfterEnter'.
	 */
	public void deserialize(String bracketSet) {
		bracketPairs = new ArrayList<org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair>();
		String[] parts = bracketSet.split(SERIAL_SEPARATOR + SERIAL_SEPARATOR);
		for (String part : parts) {
			String[] fields = part.split(SERIAL_SEPARATOR);
			if (fields.length != 4) {
				continue;
			}
			addBracketPair(fields[0], fields[1], "1".equals(fields[2]), "1".equals(fields[3]));
		}
	}
	
	/**
	 * Returns a list of bracket pairs. This call is for the list in the preference
	 * page.
	 * 
	 * @return a list of bracket pairs in the form
	 * <code>String[]{"{BRACKET_SEPARATOR}","(BRACKET_SEPARATOR)"}</code>
	 */
	public String[] getBracketArray() {
		String[] ret = new String[bracketPairs.size()];
		int i = 0;
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			ret[i] = bracketPair.getOpeningBracket() + BRACKET_SEPARATOR + bracketPair.getClosingBracket();
			i++;
		}
		return ret;
	}
	
	/**
	 * Returns this bracket set as <code>String</code>. This is useful to store the
	 * set in the <code>IPreferenceStore</code>.
	 * 
	 * @see IPreferenceStore
	 */
	public String serialize() {
		StringBuilder result = new StringBuilder();
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			result.append(bracketPair.getOpeningBracket());
			result.append(SERIAL_SEPARATOR);
			result.append(bracketPair.getClosingBracket());
			result.append(SERIAL_SEPARATOR);
			result.append(bracketPair.isClosingEnabledInside() ? "1" : "0");
			result.append(SERIAL_SEPARATOR);
			result.append(bracketPair.isCloseAfterEnter() ? "1" : "0");
			// We use two separators to indicate the boundary between two bracket pairs
			result.append(SERIAL_SEPARATOR);
			result.append(SERIAL_SEPARATOR);
		}
		return result.toString();
	}
	
	public int getCaretOffset(ISourceViewer viewer, StyledText textWidget) {
		IDocument document = viewer.getDocument();
		ProjectionViewer projectionViewer = null;
		if (viewer instanceof ProjectionViewer) {
			projectionViewer = (ProjectionViewer) viewer;
		}
		if (document == null) {
			return -1;
		}
		int caretOffset = textWidget.getCaretOffset();
		if (projectionViewer != null) {
			caretOffset = projectionViewer.widgetOffset2ModelOffset(caretOffset);
		}
		return caretOffset;
	}
	
	/**
	 * Searches the matching bracket at the left side of the caret. The position
	 * information will be stored in the <code>IDocument</code> in the category
	 * <code>ExtensionConstants.PositionCategory.BRACKET</code>.
	 */
	public void findAndHighlightMatchingBrackets(IDocument document, int caretOffset) {
		if (caretOffset <= 0) {
			return;
		}
		
		final String prevStr;
		try {
			prevStr = "" + document.getChar(caretOffset - 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return;
		}
		if (!isBracket(prevStr) || prevStr.equals(getCounterpart(prevStr))) {
			return;
		}
		boolean isForward = isOpeningBracket(prevStr);
		final String toFindStr = getCounterpart(prevStr);
		int boundary = isForward ? document.getLength() : -1;
		int position = isForward ? caretOffset : caretOffset - 2;
		String currentStr;
		int count = 0;
		try {
			while (position != boundary) {
				currentStr = "" + document.getChar(position);
				if (toFindStr.equals(currentStr) && count == 0) {
					break;
				} else if (prevStr.equals(currentStr)) {
					count++;
				} else if (currentStr.equals(toFindStr)) {
					count--;
				}
				position += isForward ? 1 : -1;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
			return;
		}
		if (position != -1 && position != document.getLength()) {
			positionHelper.addPosition(document, org.modelrefactoring.evolution.coed.resource.coed.ui.CoedPositionCategory.BRACKET.toString(), position, 1);
			positionHelper.addPosition(document, org.modelrefactoring.evolution.coed.resource.coed.ui.CoedPositionCategory.BRACKET.toString(), caretOffset - 1, 1);
		}
	}
	
	/**
	 * Checks whether the given string is an opening bracket and closing is desired
	 * after entering a line break.
	 */
	public boolean isCloseAfterEnter(String bracket) {
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			if (bracket.equals(bracketPair.getOpeningBracket())) {
				return bracketPair.isCloseAfterEnter();
			}
		}
		return false;
	}
	
	/**
	 * Checks whether the given string is an opening bracket and closing is desired
	 * right after entering this bracket.
	 */
	public boolean isCloseInstantly(String bracket) {
		for (org.modelrefactoring.evolution.coed.resource.coed.ICoedBracketPair bracketPair : bracketPairs) {
			if (bracket.equals(bracketPair.getOpeningBracket())) {
				return !bracketPair.isCloseAfterEnter();
			}
		}
		return false;
	}
	
}
