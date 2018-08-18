package ch.makery.log.services;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

public interface ISearchFileOrFolder 
{
//	void setParentFolder(Path parentFolder);
//	
//	Path getParentFolder();
	
	String findFileOrFolder(String path, String extebsion);

//	void setMostRecentFileOrFolder(Optional<File> mostRecentFileOrFolder);
}
