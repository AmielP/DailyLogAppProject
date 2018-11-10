
package ch.makery.log.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import ch.makery.log.services.ISearchFileOrDirectory;
import ch.makery.log.services.SearchSpecificFileOrFolder;

public class SearchSpecificFile extends SearchSpecificFileOrFolder implements ISearchFileOrDirectory
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
	private String mostRecentFile(ISearchFileOrDirectory findMostRecentFile, String filePath, String prefix, String extension)
	{
		return findMostRecentFile.findFileOrFolder(filePath, prefix, extension);
	}

	@Override
	public String findFileOrFolder(String filePath, String prefix, String extension) 
	{
		//Delete OR keep this when you finish with the extension thing
		FindCertainPrefixOrExtension fileExtension = new FindCertainPrefixOrExtension();

		ISearchFileOrDirectory findFile = (str1, str2, str3) -> //(filePath, prefix, extension)
		{
			setFolder(Paths.get(str1));

			defineMostRecentFile(str1, str2, str3);

			if(getFileOrDirectory().isPresent())
			{
				String getMostRecentFilePath = getFileOrDirectory().get().getPath();
				//Delete OR keep this whenever you finish with fixing the extension thing
				fileExtension.listFile(str1, str2, str3);
				return getMostRecentFilePath;
			}
			else
			{
				//Non object-oriented design structure. FIX LATER MAYBE?
				System.out.println("No log in directory. Creating temporary log file...\n");
				List<String> lines = Arrays.asList("Name:", "Date:", "Subject:", "Entry:");
				String newTextFileNameWithPath = str1 + str2 + "null" + str3;
				try 
				{
					setFolder(Paths.get(newTextFileNameWithPath));
					Files.write(getFolder(), lines, Charset.forName("UTF-8"));
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				return findFileOrFolder(str1, str2, str3);
			}
//			return null;
		};

		return mostRecentFile(findFile, filePath, prefix, extension);
	}
}