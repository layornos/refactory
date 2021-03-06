/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.refactoring.tests.properties.resource.testproperties.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Image;

public class TestpropertiesCompletionProcessor implements IContentAssistProcessor {
	
	private org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesResourceProvider resourceProvider;
	
	public TestpropertiesCompletionProcessor(org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesResourceProvider resourceProvider) {
		super();
		this.resourceProvider = resourceProvider;
	}
	
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesTextResource textResource = resourceProvider.getResource();
		if (textResource == null) {
			return new ICompletionProposal[0];
		}
		String content = viewer.getDocument().get();
		return computeCompletionProposals(textResource, content, offset);
	}
	
	public ICompletionProposal[] computeCompletionProposals(org.emftext.refactoring.tests.properties.resource.testproperties.ITestpropertiesTextResource textResource, String text, int offset) {
		org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCodeCompletionHelper helper = new org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCodeCompletionHelper();
		org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCompletionProposal[] computedProposals = helper.computeCompletionProposals(textResource, text, offset);
		
		// call completion proposal post processor to allow for customizing the proposals
		org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesProposalPostProcessor proposalPostProcessor = new org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesProposalPostProcessor();
		List<org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCompletionProposal> computedProposalList = Arrays.asList(computedProposals);
		List<org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCompletionProposal> extendedProposalList = proposalPostProcessor.process(computedProposalList);
		if (extendedProposalList == null) {
			extendedProposalList = Collections.emptyList();
		}
		List<org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCompletionProposal> finalProposalList = new ArrayList<org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCompletionProposal>();
		for (org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCompletionProposal proposal : extendedProposalList) {
			if (proposal.isMatchesPrefix()) {
				finalProposalList.add(proposal);
			}
		}
		ICompletionProposal[] result = new ICompletionProposal[finalProposalList.size()];
		int i = 0;
		for (org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesCompletionProposal proposal : finalProposalList) {
			String proposalString = proposal.getInsertString();
			String displayString = (proposal.getDisplayString()==null)?proposalString:proposal.getDisplayString();
			String prefix = proposal.getPrefix();
			Image image = proposal.getImage();
			IContextInformation info;
			info = new ContextInformation(image, displayString, proposalString);
			int begin = offset - prefix.length();
			int replacementLength = prefix.length();
			result[i++] = new CompletionProposal(proposalString, begin, replacementLength, proposalString.length(), image, displayString, info, proposalString);
		}
		return result;
	}
	
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
		return null;
	}
	
	public char[] getCompletionProposalAutoActivationCharacters() {
		IPreferenceStore preferenceStore = org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesUIPlugin.getDefault().getPreferenceStore();
		boolean enabled = preferenceStore.getBoolean(org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesPreferenceConstants.EDITOR_CONTENT_ASSIST_ENABLED);
		String triggerString = preferenceStore.getString(org.emftext.refactoring.tests.properties.resource.testproperties.ui.TestpropertiesPreferenceConstants.EDITOR_CONTENT_ASSIST_TRIGGERS);
		if(enabled && triggerString != null && triggerString.length() > 0){
			char[] triggers = new char[triggerString.length()];
			for (int i = 0; i < triggerString.length(); i++) {
				triggers[i] = triggerString.charAt(i);
			}
			return triggers;
		}
		return null;
	}
	
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}
	
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}
	
	public String getErrorMessage() {
		return null;
	}
}
