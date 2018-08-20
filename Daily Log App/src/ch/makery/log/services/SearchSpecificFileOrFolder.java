package ch.makery.log.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

public class SearchSpecificFileOrFolder implements IFileOrDirectory// extends FindMostRecentFile
{	
	private Optional<File> mostRecentFileOrFolder;
	private Path parentFolder;
	
	@Override
	public void setFileOrDirectory(Optional<File> mostRecentFileOrFolder)
	{
		this.mostRecentFileOrFolder = mostRecentFileOrFolder;
	}
	
	@Override
	public Optional<File> getFileOrDirectory()
	{
		return mostRecentFileOrFolder;
	}
	
	@Override
	public void setFolder(Path parentFolder)
	{
		this.parentFolder = parentFolder;
	}
	
	@Override
	public Path getFolder()
	{
		return parentFolder;
	}
	
	public void setParentFolder(String filePath)
	{
		parentFolder = Paths.get(filePath);
	}
	
//	Delete this method when done with feature
//	public void fMRFOF(String filePath, String extension)
//	{
//		findTextFileExtension = new FindCertainExtension();
//		findTextFileExtension.listFile(filePath, extension);
//	}
	
	public void defineMostRecentFile(String filePath, String extension)
	{	
//		parentFolder = Paths.get(filePath);
		
		mostRecentFileOrFolder = Arrays
				.stream(parentFolder.toFile().listFiles())
				.filter(f -> f.getAbsolutePath().endsWith(extension)) //For files only, uncomment this code
				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified())); //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file);
	}
	
	public void defineMostRecentFolder(String filePath)
	{	
//		parentFolder = Paths.get(filePath);
		
		mostRecentFileOrFolder = Arrays
				.stream(parentFolder.toFile().listFiles())
				.filter(f -> f.isDirectory()) //For files only, uncomment this code
				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified())); //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file);
	}
	
	//Migrate this into the sub-classes
//	private String mostRecentFile(ISearchFileOrDirectory findMostRecentFile, String filePath, String extension);
//	{
//		return findMostRecentFile.findFileOrFolder(filePath, extension);
//	}
	
//	Migrate this over to the sub-classes
////	@Override
//	public abstract String findFileOrFolder(String filePath, String extension);
//	{
//		//Delete this when you finish with the extension thing
////		fileExtension = new FindCertainExtension();
//		
//		ISearchFileOrDirectory findFile = (str1, str2) ->
//		{
//			setParentFolder(Paths.get(filePath));
//			
//			if(extension.isEmpty())
//			{
//				defineMostRecentFolder(filePath);
//			}
//			else
//			{
//				defineMostRecentFile(filePath, extension);
//			}
////				Optional<File> mostRecentFileOrFolder =
////						Arrays
////						.stream(parentFolder.toFile().listFiles())
//////						.filter(f -> f.isFile()) //For files only, uncomment this code
////						.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified())); //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file
//
//			//mostRecentFile(
//
//			if(getMostRecentFileOrFolder().isPresent())
//			{
//				String getMostRecentFilePath = getMostRecentFileOrFolder().get().getPath();
//				
//				//Delete this whenever you finish with fixing the extension thing
////				fileExtension.listFile(filePath, ".txt");
//				return getMostRecentFilePath;
//			}
//			else
//			{
//				System.out.println("ERROR: Folder is empty!");
//			}
//			return null;
//		};
//		
//		return mostRecentFile(findFile, filePath, extension);
//	}
}
