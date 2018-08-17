package ch.makery.log.services;

import java.io.File;

public interface IGenericExtensionFilter 
{
	boolean accept(File directory, String name);
}
