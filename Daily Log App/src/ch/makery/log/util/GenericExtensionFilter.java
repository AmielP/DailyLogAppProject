package ch.makery.log.util;

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
	
	@Override
	public boolean accept(File directory, String name)
	{
		return (name.endsWith(extension));
	}
}
