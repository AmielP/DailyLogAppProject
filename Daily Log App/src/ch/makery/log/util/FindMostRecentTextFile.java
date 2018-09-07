package ch.makery.log.util;

import ch.makery.log.services.FindMostRecentFile;

public class FindMostRecentTextFile extends FindMostRecentFile
{	
	private String textFilePrefix = "Entry_";
	private String textFileExtensionType = ".txt";
	
	public FindMostRecentTextFile()
	{
		setSearchFileOrFolder(new SearchSpecificFile());
	}
	
	@Override
	public String targetFileOrFolderName(String filePath)
	{
		return getSearchFileOrFolder().findFileOrFolder(filePath, textFilePrefix, textFileExtensionType);
	}
	
//	@Override
//	public String findMostRecentFile(String textFilePath)
//	{
//		Path parentFolder = Paths.get(textFilePath);
//
//		Optional<File> mostRecentFile =
//				Arrays
//				.stream(parentFolder.toFile().listFiles())
//				.filter(f -> f.isFile()) //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file
//				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
//		
//		//mostRecentFile(
//
//		if(mostRecentFile.isPresent())
//		{
//			String getMostRecentFilePath = mostRecentFile.get().getPath();
//			return getMostRecentFilePath;
//		}
//		else
//		{
//			System.out.println("ERROR: Folder is empty!");
//		}
//		return null;
//	}

	//May delete this later on
//	@Override
//	public void test(String folderPath) 
//	{
//		findFileOrFolder(folderPath);
//	}
}
