/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.modelrefactoring.guery.resource.guery.ui;

/**
 * This class finds text positions to highlight and adds them to the document.
 */
public class GueryOccurrence {
	
	private static interface ITokenScannerConstraint {
		public boolean mustStop(org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner tokenScanner);
	}
	
	public final static String OCCURRENCE_ANNOTATION_ID = "org.modelrefactoring.guery.resource.guery.ui.occurences";
	public final static String DECLARATION_ANNOTATION_ID = "org.modelrefactoring.guery.resource.guery.ui.occurences.declaration";
	
	private final static org.modelrefactoring.guery.resource.guery.ui.GueryPositionHelper positionHelper = new org.modelrefactoring.guery.resource.guery.ui.GueryPositionHelper();
	
	/**
	 * The viewer showing the document the we search occurrences for
	 */
	private org.eclipse.jface.text.source.projection.ProjectionViewer projectionViewer;
	
	/**
	 * The resource we operate on
	 */
	private org.modelrefactoring.guery.resource.guery.IGueryTextResource textResource;
	
	/**
	 * The text of the token that was located at the caret position at the time
	 * occurrence were computed last
	 */
	private String tokenText = "";
	
	/**
	 * The region of the token that was located at the caret position at the time
	 * occurrence were computed last
	 */
	private org.eclipse.jface.text.Region tokenRegion;
	
	/**
	 * Creates a GueryOccurrence object to find positions to highlight.
	 * 
	 * @param textResource the text resource for location
	 * @param projectionViewer the viewer for the text
	 */
	public GueryOccurrence(org.modelrefactoring.guery.resource.guery.IGueryTextResource textResource, org.eclipse.jface.text.source.projection.ProjectionViewer projectionViewer) {
		super();
		this.textResource = textResource;
		this.projectionViewer = projectionViewer;
		
		resetTokenRegion();
	}
	
	protected org.eclipse.emf.ecore.EObject getResolvedEObject(org.eclipse.emf.ecore.EObject eObject) {
		return eObject.eIsProxy() ? org.eclipse.emf.ecore.util.EcoreUtil.resolve(eObject, textResource) : eObject;
	}
	
	/**
	 * Tries to resolve the first proxy object in the given list.
	 * 
	 * @param objects the <code>EObject</code>s located at the caret position
	 * 
	 * @return the resolved <code>EObject</code> of the first proxy
	 * <code>EObject</code> in a list. If resolving fails, <code>null</code> is
	 * returned.
	 */
	public org.eclipse.emf.ecore.EObject tryToResolve(java.util.List<org.eclipse.emf.ecore.EObject> objects) {
		for (org.eclipse.emf.ecore.EObject object : objects) {
			if (object.eIsProxy()) {
				return getResolvedEObject(object);
			}
		}
		return null;
	}
	
	/**
	 * Returns the EObject at the current caret position.
	 */
	public org.eclipse.emf.ecore.EObject getEObjectAtCurrentPosition() {
		if (textResource == null) {
			return null;
		}
		
		int caretOffset = getCaretOffset();
		
		org.modelrefactoring.guery.resource.guery.IGueryLocationMap locationMap = textResource.getLocationMap();
		java.util.List<org.eclipse.emf.ecore.EObject> elementsAtOffset = locationMap.getElementsAt(caretOffset);
		
		if (elementsAtOffset == null || elementsAtOffset.isEmpty()) {
			return null;
		}
		
		for (org.eclipse.emf.ecore.EObject candidate : elementsAtOffset) {
			if (candidate.eIsProxy()) {
				candidate = getResolvedEObject(candidate);
			}
			// Only accept elements that are actually contained in a resource. The location
			// map might reference elements that were removed by a post processor and which
			// are therefore not part of the resource anymore.
			if (candidate.eResource() != null) {
				return candidate;
			}
		}
		return null;
	}
	
	/**
	 * Returns the text of the token that was found at the caret position at the time
	 * occurrence we computed last.
	 * 
	 * @return the token text
	 */
	protected String getTokenText() {
		return tokenText;
	}
	
	protected int getLength(org.modelrefactoring.guery.resource.guery.IGueryLocationMap locationMap, org.eclipse.emf.ecore.EObject eObject) {
		return locationMap.getCharEnd(eObject) - locationMap.getCharStart(eObject) + 1;
	}
	
	/**
	 * Finds the positions of the occurrences and declarations which will be
	 * highlighted.
	 */
	public void updateOccurrenceAnnotations() {
		if (textResource == null) {
			return;
		}
		
		final int caretOffset = getCaretOffset();
		org.eclipse.jface.text.IDocument document = getSourceViewer().getDocument();
		if (caretOffset < 0 || caretOffset >= document.getLength()) {
			// The caret is outside of the document.
			removeAnnotations();
			return;
		}
		
		if (isContainedIn(tokenRegion, caretOffset)) {
			// The caret is still contained in the same token region. No need to update
			// occurrence annotations.
			return;
		}
		
		resetTokenRegion();
		org.modelrefactoring.guery.resource.guery.IGueryLocationMap locationMap = textResource.getLocationMap();
		java.util.List<org.eclipse.emf.ecore.EObject> elementsAtOffset = locationMap.getElementsAt(caretOffset);
		
		if (elementsAtOffset == null || elementsAtOffset.size() < 1) {
			// The document does not contain EObjects. Probably there is a syntax error.
			removeAnnotations();
			return;
		}
		
		removeAnnotations();
		org.eclipse.emf.ecore.EObject resolvedEObject = tryToResolve(elementsAtOffset);
		org.eclipse.emf.ecore.EObject referencedElement;
		org.eclipse.emf.ecore.EObject firstElementAtOffset = elementsAtOffset.get(0);
		if (resolvedEObject != null) {
			referencedElement = resolvedEObject;
		} else {
			referencedElement = firstElementAtOffset;
		}
		
		// Scan the region in which the referenced object is located.
		org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner tokenScanner = scan(referencedElement, new ITokenScannerConstraint() {
			
			public boolean mustStop(org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner tokenScanner) {
				int tokenOffset = tokenScanner.getTokenOffset();
				int tokenLength = tokenScanner.getTokenLength();
				// check whether the caret in this token
				return isContainedIn(tokenOffset, tokenLength, caretOffset);
			}
		});
		
		if (tokenScanner != null) {
			// caret is located in referenced element
			removeAnnotations();
			
			int tokenOffset = tokenScanner.getTokenOffset();
			int tokenLength = tokenScanner.getTokenLength();
			tokenText = tokenScanner.getTokenText();
			tokenRegion = new org.eclipse.jface.text.Region(tokenOffset, tokenLength);
		}
		
		// The tokenScanner must always be not null if there was no proxy at the caret
		// position, but to prevent JDT from complaining about a potential null pointer
		// access, we check both conditions here.
		if (resolvedEObject == null && tokenScanner != null) {
			// caret is within definition
			int tokenOffset = tokenScanner.getTokenOffset();
			// we pass null as 'definitionText' because we do not know whether the token at
			// the caret is actually the defining name
			addAnnotations(referencedElement, null, tokenOffset, caretOffset);
		} else {
			// caret is within reference
			int proxyOffset = locationMap.getCharStart(firstElementAtOffset);
			int proxyLength = getLength(locationMap, firstElementAtOffset);
			try {
				String proxyText = document.get(proxyOffset, proxyLength);
				int index = getIndexOf(referencedElement, proxyText);
				if (index >= 0) {
					addAnnotations(referencedElement, proxyText, index, caretOffset);
				}
			} catch (org.eclipse.jface.text.BadLocationException e) {
				// ignore
			}
		}
	}
	
	protected boolean isContainedIn(org.eclipse.jface.text.Region region, int offset) {
		int regionOffset = region.getOffset();
		int regionEnd = regionOffset + region.getLength();
		return offset >= regionOffset && offset <= regionEnd;
	}
	
	protected boolean isContainedIn(int regionOffset, int regionLength, int offset) {
		int regionEnd = regionOffset + regionLength;
		return regionOffset <= offset && offset < regionEnd;
	}
	
	protected void addAnnotations(org.eclipse.emf.ecore.EObject referencedElement, String definitionText, int definitionOffset, int caretOffset) {
		java.util.List<String> matchingNames = addAnnotationsForDefinition(referencedElement, definitionText, definitionOffset, caretOffset);
		addAnnotationsForReferences(referencedElement, matchingNames);
	}
	
	protected void addAnnotationsForReferences(org.eclipse.emf.ecore.EObject referencedElement, java.util.List<String> matchingNames) {
		
		org.eclipse.jface.text.IDocument document = getSourceViewer().getDocument();
		
		// Determine all references to the EObject
		java.util.Map<org.eclipse.emf.ecore.EObject, java.util.Collection<org.eclipse.emf.ecore.EStructuralFeature.Setting>> map = org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer.find(java.util.Collections.singleton(textResource));
		java.util.Collection<org.eclipse.emf.ecore.EStructuralFeature.Setting> referencingObjects = map.get(referencedElement);
		if (referencingObjects == null) {
			// No references found
			return;
		}
		
		// Highlight the token in the text for the referencing objects
		for (org.eclipse.emf.ecore.EStructuralFeature.Setting setting : referencingObjects) {
			org.eclipse.emf.ecore.EObject referencingElement = setting.getEObject();
			// Search through all tokens in the elements that reference the element at the
			// caret position
			for (String name : matchingNames) {
				int index = getIndexOf(referencingElement, name);
				if (index > 0) {
					addAnnotation(document, org.modelrefactoring.guery.resource.guery.ui.GueryPositionCategory.PROXY, name, index, name.length());
				}
			}
		}
	}
	
	protected java.util.List<String> addAnnotationsForDefinition(org.eclipse.emf.ecore.EObject referencedElement, String definitionText, int definitionOffset, final int caretOffset) {
		
		final org.eclipse.jface.text.IDocument document = getSourceViewer().getDocument();
		final java.util.List<String> matchingNames = new java.util.ArrayList<String>();
		if (definitionText == null) {
			// The object at the caret position is not referenced from within the resource.
			// Thus, we cannot highlight occurrences or declarations.
			final java.util.List<String> names = new org.modelrefactoring.guery.resource.guery.analysis.GueryDefaultNameProvider().getNames(referencedElement);
			scan(referencedElement, new ITokenScannerConstraint() {
				
				public boolean mustStop(org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner tokenScanner) {
					int offset = tokenScanner.getTokenOffset();
					int length = tokenScanner.getTokenLength();
					String text = tokenScanner.getTokenText();
					if (names.contains(text) && isContainedIn(offset, length, caretOffset)) {
						matchingNames.add(text);
						addAnnotation(document, org.modelrefactoring.guery.resource.guery.ui.GueryPositionCategory.DEFINITION, text, offset, text.length());
					}
					return false;
				}
			});
		} else {
			// Highlight the token in the text for the referenced object
			addAnnotation(document, org.modelrefactoring.guery.resource.guery.ui.GueryPositionCategory.DEFINITION, definitionText, definitionOffset, definitionText.length());
			matchingNames.add(definitionText);
		}
		return matchingNames;
	}
	
	/**
	 * Returns the index of the given text within the text that corresponds to  the
	 * EObject.
	 */
	protected int getIndexOf(org.eclipse.emf.ecore.EObject eObject, final String text) {
		org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner tokenScanner = scan(eObject, new ITokenScannerConstraint() {
			
			public boolean mustStop(org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner tokenScanner) {
				String tokenText = tokenScanner.getTokenText();
				return tokenText.equals(text);
			}
		});
		
		if (tokenScanner == null) {
			return -1;
		} else {
			return tokenScanner.getTokenOffset();
		}
	}
	
	protected org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner scan(org.eclipse.emf.ecore.EObject object, ITokenScannerConstraint constraint) {
		org.eclipse.jface.text.IDocument document = getSourceViewer().getDocument();
		
		org.modelrefactoring.guery.resource.guery.IGueryLocationMap locationMap = textResource.getLocationMap();
		
		org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner tokenScanner = createTokenScanner();
		int offset = locationMap.getCharStart(object);
		int length = getLength(locationMap, object);
		
		tokenScanner.setRange(document, offset, length);
		org.eclipse.jface.text.rules.IToken token = tokenScanner.nextToken();
		while (!token.isEOF()) {
			if (constraint.mustStop(tokenScanner)) {
				return tokenScanner;
			}
			token = tokenScanner.nextToken();
		}
		return null;
	}
	
	protected org.modelrefactoring.guery.resource.guery.ui.IGueryTokenScanner createTokenScanner() {
		return new org.modelrefactoring.guery.resource.guery.ui.GueryUIMetaInformation().createTokenScanner(null, null);
	}
	
	protected void addAnnotation(org.eclipse.jface.text.IDocument document, org.modelrefactoring.guery.resource.guery.ui.GueryPositionCategory type, String text, int offset, int length) {
		// for declarations and occurrences we do not need to add the position to the
		// document
		org.eclipse.jface.text.Position position = positionHelper.createPosition(offset, length);
		// instead, an annotation is created
		org.eclipse.jface.text.source.Annotation annotation = new org.eclipse.jface.text.source.Annotation(false);
		if (type == org.modelrefactoring.guery.resource.guery.ui.GueryPositionCategory.DEFINITION) {
			annotation.setText("Declaration of " + text);
			annotation.setType(DECLARATION_ANNOTATION_ID);
		} else {
			annotation.setText("Occurrence of " + text);
			annotation.setType(OCCURRENCE_ANNOTATION_ID);
		}
		getSourceViewer().getAnnotationModel().addAnnotation(annotation, position);
	}
	
	protected void removeAnnotations() {
		removeAnnotations(org.modelrefactoring.guery.resource.guery.ui.GueryOccurrence.OCCURRENCE_ANNOTATION_ID);
		removeAnnotations(org.modelrefactoring.guery.resource.guery.ui.GueryOccurrence.DECLARATION_ANNOTATION_ID);
	}
	
	protected void removeAnnotations(String annotationTypeID) {
		java.util.List<org.eclipse.jface.text.source.Annotation> annotationsToRemove = new java.util.ArrayList<org.eclipse.jface.text.source.Annotation>();
		org.eclipse.jface.text.source.IAnnotationModel annotationModel = getSourceViewer().getAnnotationModel();
		java.util.Iterator<?> annotationIterator = annotationModel.getAnnotationIterator();
		while (annotationIterator.hasNext()) {
			Object object = (Object) annotationIterator.next();
			if (object instanceof org.eclipse.jface.text.source.Annotation) {
				org.eclipse.jface.text.source.Annotation annotation = (org.eclipse.jface.text.source.Annotation) object;
				if (annotationTypeID.equals(annotation.getType())) {
					annotationsToRemove.add(annotation);
				}
			}
		}
		for (org.eclipse.jface.text.source.Annotation annotation : annotationsToRemove) {
			annotationModel.removeAnnotation(annotation);
		}
	}
	
	/**
	 * Resets the token region to enable remove highlighting if the text is changing.
	 */
	public void resetTokenRegion() {
		tokenRegion = new org.eclipse.jface.text.Region(-1, 0);
	}
	
	protected int getCaretOffset() {
		org.eclipse.swt.custom.StyledText textWidget = getSourceViewer().getTextWidget();
		int widgetOffset = textWidget.getCaretOffset();
		return getTextViewerExtension5().widgetOffset2ModelOffset(widgetOffset);
	}
	
	/**
	 * Accessor method for the field <code>projectionViewer</code>. The accessor is
	 * also used for unit testing to inject a custom source viewer by overriding this
	 * method.
	 */
	protected org.eclipse.jface.text.source.ISourceViewer getSourceViewer() {
		return projectionViewer;
	}
	
	/**
	 * Accessor method for the field <code>projectionViewer</code>. The accessor is
	 * also used for unit testing to inject a custom text viewer extension by
	 * overriding this method.
	 */
	protected org.eclipse.jface.text.ITextViewerExtension5 getTextViewerExtension5() {
		return projectionViewer;
	}
	
}
