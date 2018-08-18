//package ch.makery.log.util;
//
//import java.nio.file.Paths;
//import java.util.Arrays;
//
//import ch.makery.log.services.ISearchFileOrFolder;
//
//public class SearchSpecificFile implements ISearchFileOrFolder// extends FindMostRecentFile
//{	
//	private String mostRecentFile(ISearchFileOrFolder findMostRecentFile, String filePath, String extension)
//	{
//		return findMostRecentFile.findFileOrFolder(filePath, extension);
//	}
//	
////	Delete this method when done with feature
////	public void fMRFOF(String filePath, String extension)
////	{
////		findTextFileExtension = new FindCertainExtension();
////		findTextFileExtension.listFile(filePath, extension);
////	}
//	
//	private void defineMostRecentFile(String filePath, String extension)
//	{	
//		setMostRecentFileOrFolder(Arrays
//				.stream(getParentFolder().toFile().listFiles())
//				.filter(f -> f.getAbsolutePath().endsWith(extension)) //For files only, uncomment this code
//				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()))); //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file);
//	}
//	
//	@Override
//	public String findFileOrFolder(String filePath, String extension)
//	{
//		//Delete this when you finish with the extension thing
////		fileExtension = new FindCertainExtension();
//		
//		ISearchFileOrFolder findFile = (str1, str2) ->
//		{
//			setParentFolder(Paths.get(filePath));
//			
//			if(extension.isEmpty())
//			{
//				defineMostRecentFolder(filePath);
//			}
//			else
//			{
//				defineMostRecentFile(filePath, extension);
//			}
////				Optional<File> mostRecentFileOrFolder =
////						Arrays
////						.stream(parentFolder.toFile().listFiles())
//////						.filter(f -> f.isFile()) //For files only, uncomment this code
////						.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified())); //filter is defined by its parameter, f, becoming a flag for an existent or non-existent file
//
//			//mostRecentFile(
//
//			if(getMostRecentFileOrFolder().isPresent())
//			{
//				String getMostRecentFilePath = getMostRecentFileOrFolder().get().getPath();
//				
//				//Delete this whenever you finish with fixing the extension thing
////				fileExtension.listFile(filePath, ".txt");
//				return getMostRecentFilePath;
//			}
//			else
//			{
//				System.out.println("ERROR: Folder is empty!");
//			}
//			return null;
//		};
//		
//		return mostRecentFile(findFile, filePath, extension);
//	}
//}
