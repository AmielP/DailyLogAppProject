package ch.makery.log.view;

import java.io.File;
import java.util.List;

import ch.makery.log.MainApp;
import ch.makery.log.model.LogOverviewTemplate;
import javafx.fxml.FXML;

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
	
	@FXML
	private void handleNew()
	{
		getNameTextField().clear();
		getSubjectTextField().clear();
		getEntryTextArea().clear();
	}
}
