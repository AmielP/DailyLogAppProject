package ch.makery.log.services;

public abstract class FindMostRecentFile implements IFindMostRecentFileOrFolderTemplate
{	
	private ISearchFileOrDirectory searchFile;
	private IFileOrDirectory file;
	
	@Override
	public void setSearchFileOrFolder(ISearchFileOrDirectory searchFile)
	{
		this.searchFile = searchFile;
	}
	
	@Override
	public ISearchFileOrDirectory getSearchFileOrFolder()
	{
		return searchFile;
	}
	
	@Override
	public void setFileOrFolder(IFileOrDirectory file)
	{
		this.file = file;
	}
	
	@Override
	public IFileOrDirectory getFileOrFolder()
	{
		return file;
	}
	
	@Override
	public abstract String targetFileOrFolderName(String filePath);
}
