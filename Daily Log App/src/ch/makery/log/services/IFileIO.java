package ch.makery.log.services;

import java.io.FileInputStream;

public interface IFileIO 
{
	void readI(Object readObject, String path);
	void writeO();
}
