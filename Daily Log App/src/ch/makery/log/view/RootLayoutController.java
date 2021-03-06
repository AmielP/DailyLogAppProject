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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootLayoutController extends LogOverviewTemplate
{	
	private File showOpenDialog;
	private FileInputStream fileInputStream;
	private byte[] bytesArray;
	private TextField name;
	private TextField subject;
	private TextArea entry;
//	private Stage aboutStage;
//	private LogOverviewController logOverviewController;

	public RootLayoutController()
	{
//		setLog(new Log());
//		aboutStage = new Stage();
	}

	@Override
	public void chooseFileToSaveOrOpen(List<Object> objectList, File file) 
	{	
		setSourceFile(new InputFile());
		setSaveAndOpenFileOption(new SaveAndOpenFileOption());
		getSaveAndOpenFileOption().setFileChooser(new FileChooser());
		listFile(getInitialLogFileName(), getExtensionLogFileName(), getExtensionLogFileFilter());

		showOpenDialog = getSaveAndOpenFileOption().getFileChooser().showOpenDialog(getMainApp().getPrimaryStage());
		setSavedFileState(showOpenDialog);

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
	
	//DELETE WHEN I CAN
//	private void setLOEToLinesOfEntry()
//	{
//		setLinesOfEntry(Arrays.asList("Name: " + getNameTF().getText(), "Date: " + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterVerbose()), "Subject: " + getSubjectTF().getText(), "Entry:", getEntryTA().getText()));
//		System.out.println("\nNAMETF: " + getNameTF().getText() + "\nSUBJECTTF: " + getSubjectTF().getText() + "\nENTRYTA: " + getEntryTA().getText());
//		setLOE(getLinesOfEntry());
//		System.out.println("\ngetLOE(): " + getLOE());
//	}

//	public void setLogOverviewController(LogOverviewController logOverviewController)
//	{
//		this.logOverviewController = logOverviewController;
//	}
//	
//	public LogOverviewController getLogOverviewController()
//	{
//		return logOverviewController;
//	}
	
	@FXML
	private void handleNew()
	{
		//Initialize variable to specific text fields and area for convenient naming conventions
		name = getMainApp().getLogOverviewController().getNameTextField();
		subject = getMainApp().getLogOverviewController().getSubjectTextField();
		entry = getMainApp().getLogOverviewController().getEntryTextArea();
		name.clear();
		subject.clear();
		entry.clear();
//		getNameTF().clear();
//		getSubjectTF().clear();
//		getEntryTA().clear()
//		chooseFileToSaveOrOpen(null, null);
//		getL().setLog(getNameTF().getText(), DateUtil.getZonedDateTime(), getSubjectTF().getText(), getEntryTA().getText());
//		setFilePathOfInitialChosenDirectory(showOpenDialog.getParentFile());
	}
	
	@FXML
	private void handleOpen()
	{
		chooseFileToSaveOrOpen(null, null);
	}
	
	@FXML
	private void handleSave()
	{
		handleSaveAs();
	}
	
	@FXML
	private void handleSaveAs()
	{
		//Initialize variable to specific text fields and area for convenient naming conventions
		name = getMainApp().getLogOverviewController().getNameTextField();
		subject = getMainApp().getLogOverviewController().getSubjectTextField();
		entry = getMainApp().getLogOverviewController().getEntryTextArea();
		
		getMainApp().getLogOverviewController().setLinesOfEntry(Arrays.asList("Name: " + name.getText(), "Date: " + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterVerbose()), "Subject: " + subject.getText(), "Entry:", entry.getText()));
		getMainApp().getLogOverviewController().saveAs(getMainApp().getLogOverviewController().getLinesOfEntry());
	}
	
	@FXML
	private void handleExit()
	{
		Platform.exit();
	}
	
	@FXML
	private void handleAbout()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutAlert.fxml"));
		Scene newScene;
		Stage aboutStage = new Stage();
		try
		{
			newScene = new Scene(loader.load());
		}
		catch(IOException e)
		{
			System.err.println(e);
			System.out.println(e);
			System.out.print("YOU HAVE ERRED");
			return;
		} 
		aboutStage.setScene(newScene);
		aboutStage.setTitle("About Us");
		aboutStage.initStyle(StageStyle.UTILITY);
		aboutStage.setResizable(false);
		aboutStage.setMinWidth(220);
		aboutStage.setMinHeight(250);
		aboutStage.show();
	}
}
