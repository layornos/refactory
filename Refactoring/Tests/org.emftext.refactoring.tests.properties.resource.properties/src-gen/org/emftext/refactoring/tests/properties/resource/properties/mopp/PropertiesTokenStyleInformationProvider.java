/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.refactoring.tests.properties.resource.properties.mopp;

public class PropertiesTokenStyleInformationProvider {
	
	public org.emftext.refactoring.tests.properties.resource.properties.IPropertiesTokenStyle getDefaultTokenStyle(String tokenName) {
		if ("COMMENT".equals(tokenName)) {
			return new org.emftext.refactoring.tests.properties.resource.properties.mopp.PropertiesTokenStyle(new int[] {0x00, 0x80, 0x00}, null, false, true, false, false);
		}
		if ("QUOTED_91_93".equals(tokenName)) {
			return new org.emftext.refactoring.tests.properties.resource.properties.mopp.PropertiesTokenStyle(new int[] {0x2A, 0x00, 0xFF}, null, false, false, false, false);
		}
		if ("QUOTED_60_62".equals(tokenName)) {
			return new org.emftext.refactoring.tests.properties.resource.properties.mopp.PropertiesTokenStyle(new int[] {0x2A, 0x00, 0xFF}, null, false, false, false, false);
		}
		if ("QUOTED_34_34".equals(tokenName)) {
			return new org.emftext.refactoring.tests.properties.resource.properties.mopp.PropertiesTokenStyle(new int[] {0x2A, 0x00, 0xFF}, null, false, false, false, false);
		}
		return null;
	}
	
}
