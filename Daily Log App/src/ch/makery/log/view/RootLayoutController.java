package ch.makery.log.view;

import java.io.File;
import java.util.List;

import ch.makery.log.MainApp;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.view.LogOverviewController;
import ch.makery.log.model.SaveAndOpenFileOption;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController extends LogOverviewTemplate
{
	public RootLayoutController()
	{
		setMainApp(new MainApp());
	}

	@Override
	public void chooseFileToSaveOrOpen(List<Object> objectList, File file) 
	{
		
	}
	
	@FXML
	private void handleNew()
	{
		getNameTF().clear();
		getSubjectTF().clear();
		getEntryTA().clear();
	}
}
