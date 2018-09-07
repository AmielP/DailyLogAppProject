package ch.makery.log.services;

public interface ISearchFileOrDirectory 
{
	//Move this to the child class
//	Optional<File> fileOrDirectory;
	
	String findFileOrFolder(String path, String prefix, String extension);

//	void setMostRecentFileOrFolder(Optional<File> mostRecentFileOrFolder);
}
