package ch.makery.log.services;

import java.io.File;
import java.util.List;
import java.util.prefs.Preferences;

public interface IFileIO 
{
//	void readI(Object readObject, String path);
	void writeO();
	void setFile(File file);
	File getFile();
	void setPreferences(Preferences preferences);
	Preferences getPreferences();
	void saveFile(List<Object> objectList, File file);
}
