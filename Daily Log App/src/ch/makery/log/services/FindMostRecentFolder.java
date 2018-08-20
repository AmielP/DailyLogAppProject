package ch.makery.log.services;

public abstract class FindMostRecentFolder implements IFindMostRecentFileOrFolderTemplate
{
	private ISearchFileOrDirectory searchFolder;
	private IFileOrDirectory folder;
//	private IFindMostRecentFileOrFolderTemplate test;
	
//	@Override
//	public void setTest(IFindMostRecentFileOrFolderTemplate test)
//	{
//		this.test = test;
//	}
//	
//	@Override
//	public IFindMostRecentFileOrFolderTemplate getTest()
//	{
//		return test;
//	}
	
	@Override
	public void setSearchFileOrFolder(ISearchFileOrDirectory searchFolder)
	{
		this.searchFolder = searchFolder;
	}
	
	@Override
	public ISearchFileOrDirectory getSearchFileOrFolder()
	{
		return searchFolder;
	}
	
	@Override
	public void setFileOrFolder(IFileOrDirectory folder)
	{
		this.folder = folder;
	}
	
	@Override
	public IFileOrDirectory getFileOrFolder()
	{
		return folder;
	}
	
	//May delete this later on
//	public abstract void test(String folderPath);
}
