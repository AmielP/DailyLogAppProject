package ch.makery.log.view;

//import java.beans.EventHandler;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import ch.makery.log.MainApp;
import ch.makery.log.model.Log;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.model.SaveAndOpenFileOption;
import ch.makery.log.services.FindMostRecentFile;
import ch.makery.log.services.IAlert;
import ch.makery.log.services.ILogTemplate;
import ch.makery.log.services.IMatchContent;
import ch.makery.log.util.DateUtil;
import ch.makery.log.util.FindMostRecentTextFile;
import ch.makery.log.util.MatchLogContent;
import ch.makery.log.util.ReadTextFileUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
//Need to update Java SDK
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
//Need to update Java SDK
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LogOverviewController extends LogOverviewTemplate
{
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField subjectTextField;
	@FXML
	private TextArea entryTextArea;
	@FXML
	private Label dateLabel;
	@FXML
	private Label sendLabel;

	private MainApp mainApp = new MainApp();

	private ILogTemplate log = new Log();

	private IMatchContent match = new MatchLogContent();

	private FindMostRecentFile mostRecentTextFile;

	//hard-coded path. may change later.
	private String dailyLogPathDirectoryOfAmiel = "D:/Documents (D)/Daily Log";

	//hard-coded path. may change later
	private String dailyLogPathDirectoryOfBCT = "C:/Users/User/Desktop";

	//hard-coded path. may change later
	private String dailyLogPathDirectoryOfTest;

	private String dailyLogFileOfAmiel;

	private String dailyLogFileOfBCT;

	private String dailyLogFileOfTest;

	private File fileOrFolderOfAmiel = new File(dailyLogPathDirectoryOfAmiel);

	private File fileOrFolderOfBCT = new File(dailyLogPathDirectoryOfBCT);

	private IAlert alert;

	private LogOverviewTemplate readTextFileUtil = new ReadTextFileUtil();
	
	String initialLogFileName;
	
	private String extensionLogFileName = "Text (*txt)";
	
	private String extensionLogFileFilter = "*.txt";
	
	private File file;
	
	File selectedDirectory;

	//	private String newLine = System.getProperty("line.separator");

	public LogOverviewController()
	{
		//default buffer size is 100000;
	}

	private void activatePaneOnDefaultRun(Object pane)
	{
		Platform.runLater(new Runnable()
		{
			public void run()
			{
				((Node) pane).requestFocus();
			}
		});
	}

	private void setEachTextBoxWithContent(byte[] buffer)
	{
		nameTextField.setText(match.matchLogContent(buffer, "Name: ", "Date:"));
		subjectTextField.setText(match.matchLogContent(buffer, "Subject: ", "Entry:"));
		entryTextArea.setText(match.matchLogContent(buffer, "Entry:"));
	}

	private void wrapTextArea(TextArea textArea)
	{
		textArea.setWrapText(true);
	}
	
	private void chooseExistingPath()
	{
		final Label labelSelectedDirectory = new Label();

		DirectoryChooser directoryChooser = new DirectoryChooser();
		selectedDirectory = directoryChooser.showDialog(mainApp.getPrimaryStage());
		
		if(selectedDirectory == null)
		{
			labelSelectedDirectory.setText("No Directory Selected");
		}
		else
		{
			labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
		}
	}

//	private void printLogDummy()
//	{
//		System.out.println(log.getName() + "\n" + log.getDate() + "\n" + log.getSubject() + "\n" + log.getEntry());
//	}

	private void showLogDetails()
	{
		DateUtil.updateTime(dateLabel, DateUtil.getDatePattern(), DateUtil.getDateFormatter());
		DateUtil.displayTimer();
	}

	//Change determineExistingPath() to determineBytesOfData();
	private void determineExistingPath()
	{
		dailyLogPathDirectoryOfTest = selectedDirectory.toString();
		mostRecentTextFile = new FindMostRecentTextFile();
		//		readTextFileUtil.getSourceFile().setBuffer(new byte[(int) fileOrFolderOfAmiel.length()]);

		if(!fileOrFolderOfAmiel.exists() && fileOrFolderOfBCT.exists())
		{
			System.out.println("BCT's stuff");
			dailyLogFileOfBCT = mostRecentTextFile.targetFileOrFolderName(dailyLogPathDirectoryOfBCT);
			((ReadTextFileUtil) readTextFileUtil).readTextFile(dailyLogFileOfBCT);
			setEachTextBoxWithContent(readTextFileUtil.getLogOverviewContent());
		}
		else if(!fileOrFolderOfBCT.exists() && fileOrFolderOfAmiel.exists())
		{
			System.out.println("Amiel's stuff");
			dailyLogFileOfAmiel = mostRecentTextFile.targetFileOrFolderName(dailyLogPathDirectoryOfAmiel);
			((ReadTextFileUtil) readTextFileUtil).readTextFile(dailyLogFileOfAmiel);
			setEachTextBoxWithContent(readTextFileUtil.getLogOverviewContent());
		}
		else if(!fileOrFolderOfBCT.exists() && !fileOrFolderOfAmiel.exists())
		{
//			System.out.println("The other guy's stuff");
			dailyLogFileOfTest = mostRecentTextFile.targetFileOrFolderName(dailyLogPathDirectoryOfTest); //Work on ifFileNotFoundInDirectory, then createFile
			System.out.println("dailyLogFileOfTest " + dailyLogFileOfTest);
			((ReadTextFileUtil) readTextFileUtil).readTextFile(dailyLogFileOfTest);
			setEachTextBoxWithContent(readTextFileUtil.getLogOverviewContent());
		}

		System.out.println(new String (readTextFileUtil.getSourceFile().getBuffer()));
		while(true)
		{
			System.out.print(new String (readTextFileUtil.getSourceFile().getBuffer()).length());

			if(readTextFileUtil.getSourceFile().getBuffer().length == 1)
			{
				System.out.print(" byte");
				break;
			}
			System.out.print(" bytes");
			break;
		}
		System.out.print(" of data");
	}
	
	@Override
	public void chooseFileToSave(List<Object> objectList, File file) 
	{
		initialLogFileName = "Entry_" + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterEntry());
		
		setSaveAndOpenFileOption(new SaveAndOpenFileOption());
		
		getSaveAndOpenFileOption().listFile(initialLogFileName, extensionLogFileName, extensionLogFileFilter);
		
		file = getSaveAndOpenFileOption().getFileChooser().showSaveDialog(mainApp.getPrimaryStage());
		
		if(file != null)
		{
			getSaveAndOpenFileOption().saveFile(objectList, file);
		}
	}
	
	//Empty initialization of the fields since user will custom enter data
	//with exception of the name field for the sake of default brevity
	//or if user has entered data to an existing text file; upon which,
	//Name, Subject, and Entry fields are appended with pre-existing data
	@FXML
	private void initialize()
	{
		//		findMostRecentTextFile = new FindMostRecentTextFile();// = new FindMostRecentFile();
		chooseExistingPath();
		//Do Something with this
		//		findMostRecentTextFile.setTest(new FindMostRecentTextFile(dailyLogPathDirectoryOfAmiel));//Erase: .setSearchFileOrFolder(new FindMostRecentTextFile());
		activatePaneOnDefaultRun(nameTextField);

		wrapTextArea(entryTextArea);

		nameTextField.appendText("Amiel");
		showLogDetails();

		determineExistingPath();
		//		System.out.println("Before the storm");

	}

	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}

	@FXML
	private void handleDeleteLog()
	{
		nameTextField.clear();
		subjectTextField.clear();
		entryTextArea.clear();
	}

	@FXML
	private void handleCancelLog()
	{
		Platform.exit();
	}

	@FXML
	private void handleSendLog()
	{
		//add alert to error check and make sure if folder doesn't exist that the error is handled properly.
		
		String title = "Warning";

		String headerText = "Attempting to access non-existent path: " + dailyLogPathDirectoryOfAmiel;

		String contentText = "Please send to an existing directory";

		List<Object> linesOfEntry = Arrays.asList("Name: " + nameTextField.getText(), "Date: " + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterVerbose()), "Subject: " + subjectTextField.getText(), "Entry:", entryTextArea.getText());
		log.setLog(nameTextField.getText(), DateUtil.getZonedDateTime(), subjectTextField.getText(), entryTextArea.getText());
		chooseFileToSave(linesOfEntry, file);
	}

	@FXML
	private void handleOnClickReleaseSendLog()
	{

	}
}
