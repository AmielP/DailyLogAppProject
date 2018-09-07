package ch.makery.log.util;

import java.io.File;
import java.io.FilenameFilter;

import ch.makery.log.model.GenericFilter;
import ch.makery.log.services.IFindExtension;
//import ch.makery.log.services.IGenericFilter;

public class FindCertainPrefixOrExtension implements IFindExtension
{	
	private FilenameFilter filter;
	
	@Override
	public void listFile(String fileDirectory, String prefix, String extension)
	{
		filter = new GenericFilter(prefix, extension);
		
		File directory = new File(fileDirectory);
		
		if(directory.isDirectory() == false)
		{
			System.out.println("ERROR: " + fileDirectory + " does not exist");
			return;
		}
		
		String[] list = directory.list((FilenameFilter) filter);
		
		if(list.length == 0)
		{
			System.out.println("ERROR: There are no files that exist starting with " + prefix + " or ending with " + extension);
		}
		
		for(String file : list)
		{
			String temp = new StringBuffer(fileDirectory).append(File.separator).append(file).toString();
			System.out.println("listFile: " + temp);
		}
	}
}
