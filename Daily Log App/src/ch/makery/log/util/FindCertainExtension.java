package ch.makery.log.util;

import java.io.File;
import java.io.FilenameFilter;

import ch.makery.log.services.IFindExtension;
import ch.makery.log.services.IGenericExtensionFilter;

public class FindCertainExtension implements IFindExtension
{	
	private IGenericExtensionFilter filter;
	
	@Override
	public void listFile(String fileDirectory, String extension)
	{
		filter = new GenericExtensionFilter(extension);
		
		File directory = new File(fileDirectory);
		
		if(directory.isDirectory() == false)
		{
			System.out.println("ERROR: " + fileDirectory + " does not exist");
			return;
		}
		
		String[] list = directory.list((FilenameFilter) filter);
		
		if(list.length == 0)
		{
			System.out.println("ERROR: There are no files that exist ending with " + extension);
		}
		
		for(String file : list)
		{
			String temp = new StringBuffer(fileDirectory).append(File.separator).append(file).toString();
			System.out.println(temp);
		}
	}
}
