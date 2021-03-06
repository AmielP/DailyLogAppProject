package ch.makery.log.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import ch.makery.log.MainApp;
import ch.makery.log.services.IFindExtension;
import ch.makery.log.services.ILogTemplate;
import ch.makery.log.services.IMatchContent;
import ch.makery.log.util.MatchLogContent;
import ch.makery.log.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public abstract class LogOverviewTemplate implements IFindExtension
{
	private ILogTemplate log = new Log();
	
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
	
	private static Log l;
	private static List<Object> lOE;
	private static File savedFileState;
	
	private String initialLogFileName = "Entry_";
	private String extensionLogFileName = "Text (*txt)";
	private String extensionLogFileFilter = "*.txt";
	private List<Object> linesOfEntry;
	
	private IMatchContent match = new MatchLogContent();
	private static File filePathOfInitialChosenDirectory;
	
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
	
	public void setEachTextBoxWithContent(byte[] buffer)
	{
		nameTF.setText(match.matchLogContent(buffer, "Name: ", "Date:"));
		subjectTF.setText(match.matchLogContent(buffer, "Subject: ", "Entry:"));
		entryTA.setText(match.matchLogContent(buffer, "Entry:"));
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
	
	public static void setLOE(List<Object> lOE)
	{
		LogOverviewTemplate.lOE = lOE;
	}
	
	public static List<Object> getLOE()
	{
		return lOE;
	}
	
	public static void setFilePathOfInitialChosenDirectory(File filePathOfInitialChosenDirectory)
	{
		LogOverviewTemplate.filePathOfInitialChosenDirectory = filePathOfInitialChosenDirectory;
	}
	
	public static File getFilePathOfInitialChosenDirectory()
	{
		return filePathOfInitialChosenDirectory;
	}
	
	public void setInitialLogFileName(String initialLogFileName)
	{
		this.initialLogFileName = initialLogFileName;
	}
	
	public String getInitialLogFileName()
	{
		return initialLogFileName;
	}
	
	public String getExtensionLogFileName()
	{
		return extensionLogFileName;
	}
	
	public String getExtensionLogFileFilter()
	{
		return extensionLogFileFilter;
	}
	
	public void setLog(ILogTemplate log)
	{
		this.log = log;
	}
	
	public ILogTemplate getLog()
	{
		return log;
	}
	
	public static void setL(Log l)
	{
		LogOverviewTemplate.l = l;
	}
	
	public static Log getL()
	{
		return l;
	}
	
	public void setLinesOfEntry(List<Object> linesOfEntry)
	{
		this.linesOfEntry = linesOfEntry;
	}
	
	public List<Object> getLinesOfEntry()
	{
		return linesOfEntry;
	}
	
	public static void setSavedFileState(File savedFileState)
	{
		LogOverviewTemplate.savedFileState = savedFileState;
	}
	
	public static File getSavedFileState()
	{
		return savedFileState;
	}
	
	@Override
	public void listFile(String initialName, String primaryExtensionName, String primaryExtensionFilter)
	{
		setSaveAndOpenFileOption(new SaveAndOpenFileOption());
		
		getSaveAndOpenFileOption().getFileChooser().setInitialFileName(initialName);
		
		FileChooser.ExtensionFilter extensionFilter1;
		extensionFilter1 = new FileChooser.ExtensionFilter(primaryExtensionName, primaryExtensionFilter);
		getSaveAndOpenFileOption().getFileChooser().getExtensionFilters().addAll(extensionFilter1);
	}
	
	public abstract void chooseFileToSaveOrOpen(List<Object> objectList, File file);
}
