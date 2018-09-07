package ch.makery.log.model;

import java.io.File;
import java.io.FilenameFilter;

//import ch.makery.log.services.IGenericFilter;

public class GenericFilter implements FilenameFilter
{
	private String prefix;
	private String extension;
	
	public GenericFilter(String prefix, String extension)
	{
		this.prefix = prefix;
		this.extension = extension;
	}
	
	//add a prefix log filter
	
	@Override
	public boolean accept(File directory, String name)
	{
		return (name.startsWith(prefix) && name.endsWith(extension));
	}
}
