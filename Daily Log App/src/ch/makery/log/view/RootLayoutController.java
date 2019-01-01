package ch.makery.log.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ch.makery.log.MainApp;
import ch.makery.log.model.InputFile;
import ch.makery.log.model.Log;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.view.LogOverviewController;
import ch.makery.log.model.SaveAndOpenFileOption;
import ch.makery.log.util.DateUtil;
import ch.makery.log.util.ReadTextFileUtil;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController extends LogOverviewTemplate
{	
	private File filePath;
	private File showOpenDialog;
	private FileInputStream fileInputStream;
	private byte[] bytesArray;

	public RootLayoutController()
	{
		setLog(new Log());
	}

	@Override
	public void chooseFileToSaveOrOpen(List<Object> objectList, File file) 
	{	
		setSourceFile(new InputFile());
		setSaveAndOpenFileOption(new SaveAndOpenFileOption());
		getSaveAndOpenFileOption().setFileChooser(new FileChooser());
		listFile(getInitialLogFileName(), getExtensionLogFileName(), getExtensionLogFileFilter());

		showOpenDialog = getSaveAndOpenFileOption().getFileChooser().showOpenDialog(getMainApp().getPrimaryStage());

		try 
		{
			getSourceFile().setBuffer(new byte[(int) showOpenDialog.length()]);
			bytesArray = getSourceFile().getBuffer();

			getSourceFile().setFunction(new FileInputStream(showOpenDialog));
			fileInputStream = (FileInputStream) getSourceFile().getFunction();
			fileInputStream.read(bytesArray);
			System.out.println("\n");
			for(int i = 0; i < bytesArray.length; i++)
			{
				System.out.print((char)bytesArray[i]);
			}
			setEachTextBoxWithContent(bytesArray);
			System.out.println("\n" + bytesArray.length + " byte(s) of data\n");
			fileInputStream.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.err.close();
		}
	}

	@FXML
	private void handleNew()
	{
		getNameTF().clear();
		getSubjectTF().clear();
		getEntryTA().clear();
	}

	@FXML
	private void handleOpen()
	{
		chooseFileToSaveOrOpen(null, null);
		getL().setLog(getNameTF().getText(), DateUtil.getZonedDateTime(), getSubjectTF().getText(), getEntryTA().getText());
		setFilePathOfInitialChosenDirectory(showOpenDialog.getParentFile());
	}

	@FXML
	private void handleSave() //MAKE handleSave() the same as handleSaveAs() so switch the function signatures and make them both saveAs functions because I am getting accessDenied
	{
		filePath = getFilePathOfInitialChosenDirectory();
//		File file = showOpenDialog;
		System.out.println("\nfilePath: " + filePath);
		System.out.println("\nName: " + getL().getName() + "\nDate: " + DateUtil.format(getL().getDate(), DateUtil.getDateFormatterVerbose()) + "\nSubject: " + getL().getSubject() + "\nEntry:\n" + getL().getEntry());
		
		if(filePath != null)
		{
			setLinesOfEntry(Arrays.asList("Name: " + getNameTF().getText(), "Date: " + DateUtil.format(getL().getDate(), DateUtil.getDateFormatterVerbose()), "Subject: " + getSubjectTF().getText(), "Entry:", getEntryTA().getText()));
			getSaveAndOpenFileOption().saveFile(getLinesOfEntry(), filePath);
			
			System.out.println("filePath exists");
		}
		else
		{
			handleSaveAs();
		}
	}
	
	@FXML
	private void handleSaveAs()
	{
		
	}
}
