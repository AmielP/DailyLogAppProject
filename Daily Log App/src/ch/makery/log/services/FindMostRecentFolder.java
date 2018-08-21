package ch.makery.log.services;

public abstract class FindMostRecentFolder implements IFindMostRecentFileOrFolderTemplate
{
	private ISearchFileOrDirectory searchFolder;
	private IFileOrDirectory folder;
	
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

	@Override
	public abstract String targetFileOrFolderName(String folderPath);
}
