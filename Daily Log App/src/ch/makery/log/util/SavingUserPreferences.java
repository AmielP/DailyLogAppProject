package ch.makery.log.util;

import java.io.File;
import java.util.prefs.Preferences;

import ch.makery.log.MainApp;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.model.OutputFile;

public class SavingUserPreferences extends LogOverviewTemplate
{
	public SavingUserPreferences()
	{
		setDestinationFile(new OutputFile());	
	}
	
	public void setLogFilePath(File file) 
	{	
		getDestinationFile().setPreferences(Preferences.userNodeForPackage(MainApp.class));
		if(file != null)
		{
			getDestinationFile().getPreferences().put("filePath", file.getPath());
			
			getPrimStage().getPrimaryStage().setTitle("DailyLogApp - " + file.getName());
		}
		else
		{
			getDestinationFile().getPreferences().remove("filePath");
			
			getPrimStage().getPrimaryStage().setTitle("DailyLogApp");
		}
	}

	public File getLogFilePath() 
	{
		getDestinationFile().setPreferences(Preferences.userNodeForPackage(MainApp.class));
		String filePath = getDestinationFile().getPreferences().get("filePath", null);
		if(filePath != null)
		{
			getSourceFile().setFile(new File(filePath));
			return getSourceFile().getFile();
		}
		else
		{
			return null;
		}
	}

	@Override
	public void readTextFile(Object selectUserPath) 
	{
		// TODO Auto-generated method stub
		
	}

}
