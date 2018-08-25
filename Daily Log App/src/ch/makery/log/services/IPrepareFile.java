package ch.makery.log.services;

import java.io.File;
import java.io.IOException;

public interface IPrepareFile 
{
	String prepareFile(String fileName) throws IOException;
	void setFile(File file);
	File getFile();
	void setFunction(Object function);
	Object getFunction();
}
