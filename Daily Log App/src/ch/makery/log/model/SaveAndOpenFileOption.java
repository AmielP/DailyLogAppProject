package ch.makery.log.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import ch.makery.log.services.IFileIO;
import ch.makery.log.services.IFindExtension;
import ch.makery.log.view.LogOverviewController;
import javafx.stage.FileChooser;

public class SaveAndOpenFileOption implements IFileIO, IFindExtension
{
	private FileChooser fileChooser;
	
	public SaveAndOpenFileOption()
	{
		fileChooser = new FileChooser();
	}

	@Override
	public void writeO() 
	{
		// TODO Nothing
	}

	@Override
	public void setFile(File file) 
	{
		// TODO Nothing
	}

	@Override
	public File getFile() 
	{
		// TODO Nothing
		return null;
	}

	@Override
	public void setPreferences(Preferences preferences) 
	{
		// TODO Nothing
	}

	@Override
	public Preferences getPreferences() 
	{
		// TODO Nothing
		return null;
	}
	
	public void setFileChooser(FileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}
	
	public FileChooser getFileChooser()
	{
		return fileChooser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveFile(List<Object> objectList, File file) 
	{	
		try
		{
			Files.write(file.toPath(), (List<String>)(Object)objectList, Charset.forName("UTF-8"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Logger.getLogger(LogOverviewController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void listFile(String initialName, String primaryExtensionName, String primaryExtensionFilter) 
	{
		fileChooser.setInitialFileName(initialName);
		
		FileChooser.ExtensionFilter extensionFilter1, extensionFilter2;
		extensionFilter1 = new FileChooser.ExtensionFilter(primaryExtensionName, primaryExtensionFilter);
		extensionFilter2 = new FileChooser.ExtensionFilter("all files (*)", "*.*");
		fileChooser.getExtensionFilters().addAll(extensionFilter1, extensionFilter2);
	}
}
