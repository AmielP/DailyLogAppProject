package ch.makery.log.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

import ch.makery.log.services.FindMostRecentFolder;

public class FindMostRecentUnzippedFolder extends FindMostRecentFolder
{	
	private Path parentFolder;
	
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
	
	
	public void setParentFolder(Path parentFolder)
	{
		this.parentFolder = parentFolder;
	}
	
	public Path getParentFolder()
	{
		return parentFolder;
	}
}
