package ch.makery.log.services;

public interface IFindMostRecentFileOrFolderTemplate 
{
//	void setTest(IFindMostRecentFileOrFolderTemplate test);
	void setSearchFileOrFolder(ISearchFileOrDirectory searchFileOrFolder);
	ISearchFileOrDirectory getSearchFileOrFolder();
	void setFileOrFolder(IFileOrDirectory fileOrFolder);
	IFileOrDirectory getFileOrFolder();
//	IFindMostRecentFileOrFolderTemplate getTest();
}
