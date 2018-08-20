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
	
	//Migrate this into the SearchSpecificFileOrFolder class
//	private Optional<File> mostRecentFileOrFolder;

	//Migrate this into the SearchSpecificFileOrFolder class
//	private Path parentFolder;
	
//	@Override
//	public String findMostRecentFile(String textFilePath)
//	{
//		Path parentFolder = Paths.get(textFilePath);
//
//		Optional<File> mostRecentFile =
//				Arrays
//				.stream(parentFolder.toFile().listFiles())
//				.filter(f -> f.isFile()) //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file
//				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
//		
//		//mostRecentFile(
//
//		if(mostRecentFile.isPresent())
//		{
//			String getMostRecentFilePath = mostRecentFile.get().getPath();
//			return getMostRecentFilePath;
//		}
//		else
//		{
//			System.out.println("ERROR: Folder is empty!");
//		}
//		return null;
//	}
	
//	public void setSearchFileOrFolder(ISearchFileOrDirectory searchFileOrDirectory)
//	{
//		this.searchFileOrDirectory = searchFileOrDirectory;
//	}
	
	//Migrate this into the SearchSpecificFileOrFolder class
//	public void setMostRecentFileOrFolder(Optional<File> mostRecentFileOrFolder)
//	{
//		this.mostRecentFileOrFolder = mostRecentFileOrFolder;
//	}
	
	//Migrate this into the SearchSpecificFileOrFolder class
//	public Optional<File> getMostRecentFileOrFolder()
//	{
//		return mostRecentFileOrFolder;
//	}
	
//	public void setFindTextFileExtension(IFindExtension fileExtension)
//	{
//		this.fileExtension = fileExtension;
//	}
	
	//Migrate this into the SearchSpecificFileOrFolder class
//	public void setParentFolder(Path parentFolder)
//	{
//		this.parentFolder = parentFolder;
//	}
	
	//Migrate this into the SearchSpecificFileOrFolder class
//	public Path getParentFolder()
//	{
//		return parentFolder;
//	}

	//Migrate this into the SearchSpecificFileOrFolder class
//	@Override
//	public abstract String findFileOrFolder(String path, String extension);
}
