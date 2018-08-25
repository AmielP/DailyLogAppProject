package ch.makery.log.model;

import java.io.File;
import java.io.FilenameFilter;

import ch.makery.log.services.IGenericExtensionFilter;

public class GenericExtensionFilter implements IGenericExtensionFilter, FilenameFilter
{
	private String extension;
	
	public GenericExtensionFilter(String extension)
	{
		this.extension = extension;
	}
	
	//add a prefix log filter
	
	@Override
	public boolean accept(File directory, String name)
	{
		return (name.endsWith(extension));
	}
}
