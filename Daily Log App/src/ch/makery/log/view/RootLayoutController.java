package ch.makery.log.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ch.makery.log.MainApp;
import ch.makery.log.model.InputFile;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.view.LogOverviewController;
import ch.makery.log.model.SaveAndOpenFileOption;
import ch.makery.log.util.ReadTextFileUtil;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController extends LogOverviewTemplate
{	
	private File showOpenDialog;
	private FileInputStream fileInputStream;
	private byte[] bytesArray;

	public RootLayoutController()
	{
		setMainApp(new MainApp());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
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
	}

	@FXML
	private void handleSave()
	{
		File filePath = getFilePathOfInitialChosenDirectory();
//		System.out.println("\nfilePath: " + filePath);
		System.out.println("lOE: " + getLOE());
		
		if(filePath != null)
		{
//			getSaveAndOpenFileOption().saveFile(getLOE(), filePath);
//			for(int i = 0; i < bytesArray.length; i++)
//			{
//				System.out.print((char)bytesArray[i]);
//			}
			
			
			//HANDLE THE SAVE FUNCTION BY SAVING TO THE INITIALLY OPENED DIRECTORY
			
			System.out.println("In here");
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
