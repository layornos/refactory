package org.emftext.refactoring.indexconnector.sokan;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.reuseware.sokan.ID;
import org.reuseware.sokan.IndexMetaData;
import org.reuseware.sokan.IndexRow;
import org.reuseware.sokan.index.DependencyMap;
import org.reuseware.sokan.index.indexer.Indexer;
import org.reuseware.sokan.index.util.IndexUtil;
import org.reuseware.sokan.index.util.ResourceUtil;

public class InverseModelReferencesIndexer implements Indexer {

	protected static final String INDEXER_ID = "org.emftext.refactoring.indexer.inversereferences";
	protected static final String KEY_INVERSE_REFERENCED_RESOURCES	= "INVERSE_REFERENCED_RESOURCES";
	
	public InverseModelReferencesIndexer() {
		// TODO Auto-generated constructor stub
	}

	public void createIndex(URI artifactURI, IndexMetaData metaData, ResourceSet resourceSet) {
		String platformString = artifactURI.toPlatformString(true);
		List<IndexRow> index = IndexUtil.INSTANCE.getIndex();
		for (IndexRow indexRow : index) {
			IndexMetaData rowMetaData = indexRow.getMetaData();
			List<String> values = rowMetaData.getMulti(ModelReferencesIndexer.KEY_REFERENCED_RESOURCES);
			if(values != null){
				for (String value : values) {
					if(platformString.equals(value)){
						String inverseReference = ResourceUtil.uriFrom(indexRow.getArtifactID()).toPlatformString(true);
						metaData.addMultiple(KEY_INVERSE_REFERENCED_RESOURCES, inverseReference);
						System.out.println("set inverse reference in " + platformString + " for " + inverseReference);
					}
				}
			}
		}
		
//		ID refereeID = ResourceUtil.idFrom(artifactURI);
//		if(refereeID == null){
//			return;
//		}
//		IndexRow row = IndexUtil.INSTANCE.getIndex(refereeID);
//		if(row == null){
//			return;
//		}
//		List<String> values = row.getMetaData().getMulti(ModelReferencesIndexer.KEY_REFERENCED_RESOURCES);
//		for (String string : values) {
//			URI referer = URI.createPlatformResourceURI(string, true);
//			ID refererID = ResourceUtil.idFrom(referer);
//			if(refererID == null){
//				return;
//			}
//			IndexRow refererRow = IndexUtil.INSTANCE.getIndex(refererID);
//			if(row == null){
//				return;
//			}
//			IndexMetaData refererMetaData = refererRow.getMetaData();
//			String platformString = artifactURI.toPlatformString(true);
//			refererMetaData.addMultiple(KEY_INVERSE_REFERENCED_RESOURCES, platformString);
//			IFile refererFile = ResourceUtil.fileFor(referer);
//			IndexUtil.INSTANCE.update(refererFile, true);
//			System.out.println("set inverse reference in " + referer + " for " + artifactURI);
//		}
	}

	public void getDependent(ID artifactID, DependencyMap depMap) {
//		depMap.put(artifactID, Arrays.asList(new String[]{ModelReferencesIndexer.INDEXER_ID, INDEXER_ID}));
	}

}
