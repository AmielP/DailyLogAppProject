package ch.makery.log.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

import ch.makery.log.services.IFindMostRecentFileOrFolder;

public class FindMostRecentTextFile implements IFindMostRecentFileOrFolder
{
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
	
	private String mostRecentTextFile(IFindMostRecentFileOrFolder findMostRecentTextFile, String textFilePath)
	{
		return findMostRecentTextFile.findMostRecentFileOrFolder(textFilePath);
	}
	
	@Override
	public String findMostRecentFileOrFolder(String filePath)
	{
		IFindMostRecentFileOrFolder findFile = (str) ->
		{
			Path parentFolder = Paths.get(filePath);

			Optional<File> mostRecentFileOrFolder =
					Arrays
					.stream(parentFolder.toFile().listFiles())
//					.filter(f -> f.isFile()) //For files only, uncomment this code
					.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified())); //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file

			//mostRecentFile(

			if(mostRecentFileOrFolder.isPresent())
			{
				String getMostRecentFilePath = mostRecentFileOrFolder.get().getPath();
				return getMostRecentFilePath;
			}
			else
			{
				System.out.println("ERROR: Folder is empty!");
			}
			return null;
		};
		
		return mostRecentTextFile(findFile, filePath);
	}
}
