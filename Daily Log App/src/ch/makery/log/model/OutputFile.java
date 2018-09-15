package ch.makery.log.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.prefs.Preferences;

import ch.makery.log.services.IFileIO;

public class OutputFile implements IFileIO
{
	private Preferences preferences;

//	@Override
//	public void readI(Object readObject, String path) {}

	@Override
	public void writeO() 
	{
		// TODO Auto-generated method stub
		//TODO TODO TODO
	}

	@Override
	public void setFile(File file) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public File getFile() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPreferences(Preferences preferences) 
	{
		this.preferences = preferences;
	}

	@Override
	public Preferences getPreferences() 
	{
		return preferences;
	}
	
}
