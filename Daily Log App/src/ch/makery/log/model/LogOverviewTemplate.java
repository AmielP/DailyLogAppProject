package ch.makery.log.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import ch.makery.log.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class LogOverviewTemplate 
{
	private InputFile sourceFile;
	private OutputFile destinationFile;
	private PrimaryStage primStage;
	private FileInputStream logOverviewStream;
	private SaveAndOpenFileOption saveAndOpenFileOption;
	private byte[] logOverviewContent;
	private MainApp mainApp;
	
	private static TextField nameTF;
	private static TextField subjectTF;
	private static TextArea entryTA;
	
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField subjectTextField;
	@FXML
	private TextArea entryTextArea;
	
	public LogOverviewTemplate()
	{
		mainApp = new MainApp();
		saveAndOpenFileOption = new SaveAndOpenFileOption();
	}
	
	public void setSourceFile(InputFile sourceFile)
	{
		this.sourceFile = sourceFile;
	}
	
	//Remove if not needed
	public InputFile getSourceFile()
	{
		return sourceFile;
	}
	
	public void setDestinationFile(OutputFile destinationFile)
	{
		this.destinationFile = destinationFile;
	}
	
	//Remove if not needed
	public OutputFile getDestinationFile()
	{
		return destinationFile;
	}
	
	public void setLogOverviewStream(FileInputStream logOverviewStream)
	{
		this.logOverviewStream = logOverviewStream;
	}
	
	public FileInputStream getLogOverviewStream()
	{
		return logOverviewStream;
	}
	
	public void setLogOverviewContent(byte[] logOverviewContent)
	{
		this.logOverviewContent = logOverviewContent;
	}
	
	public byte[] getLogOverviewContent()
	{
		return logOverviewContent;
	}
	
	public void setPrimStage(PrimaryStage primStage)
	{
		this.primStage = primStage;
	}
	
	public PrimaryStage getPrimStage()
	{
		return primStage;
	}
	
	public void setSaveAndOpenFileOption(SaveAndOpenFileOption saveAndOpenFileOption)
	{
		this.saveAndOpenFileOption = saveAndOpenFileOption;
	}
	
	public SaveAndOpenFileOption getSaveAndOpenFileOption()
	{
		return saveAndOpenFileOption;
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
	
	public MainApp getMainApp()
	{
		return mainApp;
	}
	
	public void setNameTextField(TextField nameTextField)
	{
		this.nameTextField = nameTextField;
	}
	
	public TextField getNameTextField()
	{
		return nameTextField;
	}
	
	public void setSubjectTextField(TextField subjectTextField)
	{
		this.subjectTextField = subjectTextField;
	}
	
	public TextField getSubjectTextField()
	{
		return subjectTextField;
	}
	
	public void setEntryTextArea(TextArea entryTextArea)
	{
		this.entryTextArea = entryTextArea;
	}
	
	public TextArea getEntryTextArea()
	{
		return entryTextArea;
	}
	
	public static void setNameTF(TextField nameTF)
	{
		LogOverviewTemplate.nameTF = nameTF;
	}
	
	public static TextField getNameTF()
	{
		return nameTF;
	}
	
	public static void setSubjectTF(TextField subjectTF)
	{
		LogOverviewTemplate.subjectTF = subjectTF;
	}
	
	public static TextField getSubjectTF()
	{
		return subjectTF;
	}
	
	public static void setEntryTA(TextArea entryTA)
	{
		LogOverviewTemplate.entryTA = entryTA;
	}
	
	public static TextArea getEntryTA()
	{
		return entryTA;
	}
	
	public abstract void chooseFileToSaveOrOpen(List<Object> objectList, File file);
}
