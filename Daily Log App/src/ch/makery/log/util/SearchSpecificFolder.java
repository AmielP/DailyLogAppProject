
package ch.makery.log.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import ch.makery.log.services.FindMostRecentFolder;
import ch.makery.log.services.ISearchFileOrFolder;

public class SearchSpecificFolder implements ISearchFileOrFolder
{
	Path parentFolder;
	
//	@Override
//	public Path getParentFolder() 
//	{
//		return null;
//	}
//	
//	@Override
//	public void setParentFolder(Path parentFolder) 
//	{
//		
//	}
	
	//FIX THIS
//	private void defineMostRecentFolder(String filePath)
//	{	
//		parentFolder = Paths.get(filePath);
//		
//		setMostRecentFileOrFolder(Arrays
//				.stream(parentFolder.toFile().listFiles())
////				.filter(f -> f.isFile()) //For files only, uncomment this code
//				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()))); //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file);
//	}

	@Override
	public String findFileOrFolder(String path, String extebsion) 
	{
		return null;
	}
}
