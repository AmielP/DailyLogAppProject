package ch.makery.log.view;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
//Need to update Java SDK
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//Need to update Java SDK
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LogOverviewController extends LogOverviewTemplate
{
	@FXML
	private Label dateLabel;
	@FXML
	private Label sendLabel;

	//	private ILogTemplate log = new Log();

	private FindMostRecentFile mostRecentTextFile;

	//hard-coded path. may change later.
	private String dailyLogPathDirectoryOfAmiel = "D:/Documents (D)/Daily Log";

	//hard-coded path. may change later
	private String dailyLogPathDirectoryOfTest;

	private String dailyLogFileOfAmiel;

	private String dailyLogFileOfTest;

	private File fileOrFolderOfAmiel = new File(dailyLogPathDirectoryOfAmiel);

	private IAlert alert;

	private LogOverviewTemplate readTextFileUtil = new ReadTextFileUtil();

	private File file;

	private File selectedDirectory;

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

	private void wrapTextArea(TextArea textArea)
	{
		textArea.setWrapText(true);
	}

	private void chooseExistingPath()
	{
		final Label labelSelectedDirectory = new Label();

		DirectoryChooser directoryChooser = new DirectoryChooser();
		selectedDirectory = directoryChooser.showDialog(getMainApp().getPrimaryStage());

		if(selectedDirectory == null)
		{
			labelSelectedDirectory.setText("No Directory Selected");
			System.out.println("Did not select a directory");
			System.exit(0);
		}
		else
		{
			labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
			setFilePathOfInitialChosenDirectory(selectedDirectory);
		}
	}

	private void showLogDetails()
	{
		DateUtil.updateTime(dateLabel, DateUtil.getDatePattern(), DateUtil.getDateFormatter());
		DateUtil.displayTimer();
	}

	//Change determineExistingPath() to determineBytesOfData();
	private void determineExistingPath()
	{
		mostRecentTextFile = new FindMostRecentTextFile();
		//		readTextFileUtil.getSourceFile().setBuffer(new byte[(int) fileOrFolderOfAmiel.length()]);

		if(fileOrFolderOfAmiel.exists())
		{
			System.out.println("Amiel's stuff");
			dailyLogFileOfAmiel = mostRecentTextFile.targetFileOrFolderName(dailyLogPathDirectoryOfAmiel);
			file = new File(dailyLogFileOfAmiel);
			((ReadTextFileUtil) readTextFileUtil).readTextFile(dailyLogFileOfAmiel);
			setEachTextBoxWithContent(readTextFileUtil.getLogOverviewContent());
		}
		else
		{
			chooseExistingPath();

			dailyLogPathDirectoryOfTest = selectedDirectory.toString();
			dailyLogFileOfTest = mostRecentTextFile.targetFileOrFolderName(dailyLogPathDirectoryOfTest); 
			file = new File(dailyLogFileOfTest);
			setSavedFileState(file);
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
	public void chooseFileToSaveOrOpen(List<Object> objectList, File file) 
	{
		setInitialLogFileName("Entry_" + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterEntry()));

		getSaveAndOpenFileOption().listFile(getInitialLogFileName(), getExtensionLogFileName(), getExtensionLogFileFilter());

		file = getSaveAndOpenFileOption().getFileChooser().showSaveDialog(getMainApp().getPrimaryStage());

		if(file != null)
		{
			getSaveAndOpenFileOption().saveFile(objectList, file);

			ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageSentAlert.fxml"));
			Scene newScene;
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

			Stage messageSentAlertPopupStage = new Stage();
			messageSentAlertPopupStage.setScene(newScene);
			messageSentAlertPopupStage.initStyle(StageStyle.UNDECORATED);
			messageSentAlertPopupStage.show();

			executor.submit(() -> Platform.runLater(messageSentAlertPopupStage::show));
			executor.schedule(
					() -> Platform.runLater(() -> 
					((Stage)messageSentAlertPopupStage.getScene().getWindow()).close())
					, 1
					, TimeUnit.SECONDS);

			executor.shutdown();
		}
	}

	public void saveAs(List<Object> objectList)
	{
		chooseFileToSaveOrOpen(objectList, file);
	}

	//Empty initialization of the fields since user will custom enter data
	//with exception of the name field for the sake of default brevity
	//or if user has entered data to an existing text file; upon which,
	//Name, Subject, and Entry fields are appended with pre-existing data
	@FXML
	private void initialize()
	{
		setLog(new Log());

		setNameTF(getNameTextField());
		setSubjectTF(getSubjectTextField());
		setEntryTA(getEntryTextArea());
		//		findMostRecentTextFile = new FindMostRecentTextFile();// = new FindMostRecentFile();
		//Do Something with this
		//		findMostRecentTextFile.setTest(new FindMostRecentTextFile(dailyLogPathDirectoryOfAmiel));//Erase: .setSearchFileOrFolder(new FindMostRecentTextFile());
		activatePaneOnDefaultRun(getNameTextField());

		wrapTextArea(getEntryTextArea());

		getNameTextField().appendText("Amiel");
		showLogDetails();

		determineExistingPath();

		getLog().setLog(getNameTextField().getText(), DateUtil.getZonedDateTime(), getSubjectTextField().getText(), getEntryTextArea().getText());
		setL((Log) getLog());
		setLinesOfEntry(Arrays.asList("Name: " + getNameTextField().getText(), "Date: " + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterVerbose()), "Subject: " + getSubjectTextField().getText(), "Entry:", getEntryTextArea().getText()));
		setLOE(getLinesOfEntry());
		System.out.println("\ngetLOE() in initialize: " + getLOE());
		//		System.out.println("\nName: " + log.getName() + "\nDate: " + DateUtil.format(log.getDate(), DateUtil.getDateFormatterVerbose()) + "\nSubject: " + log.getSubject() + "\nEntry: " + log.getEntry());
		//		System.out.println("Before the storm");

	}

	@FXML
	private void handleDeleteLog()
	{
		getNameTextField().clear();
		getSubjectTextField().clear();
		getEntryTextArea().clear();
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

		setLinesOfEntry(Arrays.asList("Name: " + getNameTextField().getText(), "Date: " + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterVerbose()), "Subject: " + getSubjectTextField().getText(), "Entry:", getEntryTextArea().getText()));
		//		log.setLog(getNameTextField().getText(), DateUtil.getZonedDateTime(), getSubjectTextField().getText(), getEntryTextArea().getText());
		chooseFileToSaveOrOpen(getLinesOfEntry(), file);
	}

	//Pseudo FXML eventHandler
	public void handleSave()
	{
		handleSendLog();
	}
	
	//Pseudo FXML eventHandler
	public void handleExit()
	{
		handleCancelLog();
	}
}