package ch.makery.log.services;

import java.io.File;

public interface IFileIO 
{
//	void readI(Object readObject, String path);
	void writeO();
	void setFile(File file);
	File getFile();
}
