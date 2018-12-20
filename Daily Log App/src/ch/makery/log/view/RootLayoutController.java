package ch.makery.log.view;

import java.io.File;
import java.util.List;

import ch.makery.log.MainApp;
import ch.makery.log.model.LogOverviewTemplate;

public class RootLayoutController extends LogOverviewTemplate
{
	public RootLayoutController()
	{
		setMainApp(new MainApp())
;	}

	@Override
	public void chooseFileToSave(List<Object> objectList, File file) 
	{
		// TODO Auto-generated method stub
		
	}
}
