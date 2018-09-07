
package ch.makery.log.util;

import java.nio.file.Paths;

import ch.makery.log.services.ISearchFileOrDirectory;
import ch.makery.log.services.SearchSpecificFileOrFolder;

public class SearchSpecificFolder extends SearchSpecificFileOrFolder implements ISearchFileOrDirectory
{
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

	//Figure out how to implement this method if it remains necessary to do so
	private String mostRecentFile(ISearchFileOrDirectory findMostRecentFolder, String folderPath, String prefix, String unusedExtension)
	{
		return findMostRecentFolder.findFileOrFolder(folderPath, prefix, unusedExtension);
	}

	@Override
	public String findFileOrFolder(String folderPath, String prefix, String unusedExtension) 
	{
		//Delete this when you finish with the extension thing
		//	fileExtension = new FindCertainExtension();

		ISearchFileOrDirectory findFolder = (str1, str2, str3) ->
		{
			setFolder(Paths.get(folderPath));

			defineMostRecentFolder(folderPath);

			if(getFileOrDirectory().isPresent())
			{
				String getMostRecentFilePath = getFileOrDirectory().get().getPath();

				//Delete this whenever you finish with fixing the extension thing
				//			fileExtension.listFile(filePath, ".txt");
				return getMostRecentFilePath;
			}
			else
			{
				System.out.println("ERROR: Folder is empty!");
			}
			return null;
		};

		return mostRecentFile(findFolder, folderPath, prefix, unusedExtension);
	}
}
