package ch.makery.log.view;

//import java.beans.EventHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.makery.log.MainApp;
import ch.makery.log.model.Log;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.services.ISearchFileOrDirectory;
import ch.makery.log.services.FindMostRecentFile;
import ch.makery.log.services.IAlert;
import ch.makery.log.services.ILogTemplate;
import ch.makery.log.services.IMatchContent;
import ch.makery.log.util.AlertUtil;
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
//Need to update Java SDK
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class LogOverviewController
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

	private MainApp mainApp;

	private ILogTemplate log = new Log();

	private IMatchContent match = new MatchLogContent();

	private FindMostRecentFile mostRecentTextFile;

	//hard-coded path. may change later.
	private String dailyLogPathDirectoryOfAmiel = "D:/Documents (D)/Daily Log";

	//hard-coded path. may change later
	private String dailyLogPathDirectoryOfBCT = "C:/Users/User/Desktop";

	//hard-coded path. may change later
	private String dailyLogPathDirectoryOfTest = "D:/TestDailyLog";

	private String dailyLogFileOfAmiel;

	private String dailyLogFileOfBCT;

	private String dailyLogFileOfTest;

	private File fileOrFolderOfAmiel = new File(dailyLogPathDirectoryOfAmiel);

	private File fileOrFolderOfBCT = new File(dailyLogPathDirectoryOfBCT);

	private IAlert alert;

	private LogOverviewTemplate readTextFileUtil = new ReadTextFileUtil();

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

	//Delete this whenever possible
	//	private void readTextFile(String selectUserPath)
	//	{		
	//		try
	//		{
	//			byte[] buffer = new byte[100000];
	//
	//			FileInputStream inputStream = new FileInputStream(selectUserPath);
	//
	//			int nRead = 0;
	//
	//			while((nRead = inputStream.read(buffer)) != -1){}
	//
	//			//put match here
	//			nameTextField.setText(match.matchLogContent(buffer, "Name: ", "Date:"));
	//			subjectTextField.setText(match.matchLogContent(buffer, "Subject: ", "Entry:"));
	//			entryTextArea.setText(match.matchLogContent(buffer, "Entry:"));
	//
	//			inputStream.close();
	//		}
	//		catch(FileNotFoundException e)
	//		{
	//			System.out.println("ERROR: Unable to open file, " + selectUserPath);
	//		}
	//		catch(IOException e)
	//		{
	//			System.out.println("ERROR: Unable to read file, " + selectUserPath);
	//		}
	//	}

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

	private void printLogDummy()
	{
		System.out.println(log.getName() + "\n" + log.getDate() + "\n" + log.getSubject() + "\n" + log.getEntry());
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

		if(!fileOrFolderOfAmiel.exists() && fileOrFolderOfBCT.exists())
		{
			System.out.println("BCT's stuff");
			dailyLogFileOfBCT = mostRecentTextFile.targetFileOrFolderName(dailyLogPathDirectoryOfBCT);
			readTextFileUtil.readTextFile(dailyLogFileOfBCT);
			setEachTextBoxWithContent(readTextFileUtil.getLogOverviewContent());
		}
		else if(!fileOrFolderOfBCT.exists() && fileOrFolderOfAmiel.exists())
		{
			System.out.println("Amiel's stuff");
			dailyLogFileOfAmiel = mostRecentTextFile.targetFileOrFolderName(dailyLogPathDirectoryOfAmiel);
			readTextFileUtil.readTextFile(dailyLogFileOfAmiel);
			setEachTextBoxWithContent(readTextFileUtil.getLogOverviewContent());
		}
		else if(!fileOrFolderOfBCT.exists() && !fileOrFolderOfAmiel.exists())
		{
			System.out.println("The other guy's stuff");
			dailyLogFileOfTest = mostRecentTextFile.targetFileOrFolderName(dailyLogPathDirectoryOfTest);
			readTextFileUtil.readTextFile(dailyLogFileOfTest);
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
	
	//make OOP later
	private void SaveFile(List<String> linesOfEntry, File file)
	{
		try
		{
			Files.write(file.toPath(), linesOfEntry, Charset.forName("UTF-8"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Logger.getLogger(LogOverviewController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	//make OOP later
	private void chooseFileToSave(List<String> linesOfEntry)
	{
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.setInitialFileName("Entry_" + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterEntry()));
		
		FileChooser.ExtensionFilter extensionFilter1, extensionFilter2;
		extensionFilter1 = new FileChooser.ExtensionFilter("TXT files (*txt)", "*.txt");
		extensionFilter2 = new FileChooser.ExtensionFilter("all files (*)", "*.*");
		fileChooser.getExtensionFilters().addAll(extensionFilter1, extensionFilter2);
		
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	
		if(file != null)
		{
			SaveFile(linesOfEntry, file);
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
		//First: make a new window for save file option
		//linesOfEntry list of log string objects + log.setlog to value of log properties (i.e, name, date, subject, entry) +
		//file + createNewFile() + Files.write need to go in different class and program (maybe) to an existing interface
		//add alert to error check and make sure if folder doesn't exist that the error is handled properly.
		
		String title = "Warning";

		String headerText = "Attempting to access non-existent path: " + dailyLogPathDirectoryOfAmiel;

		String contentText = "Please send to an existing directory";

		//		DateUtil.updateTime(dateLabel);
		List<String> linesOfEntry = Arrays.asList("Name: " + nameTextField.getText(), "Date: " + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterVerbose()), "Subject: " + subjectTextField.getText(), "Entry:", entryTextArea.getText());
		log.setLog(nameTextField.getText(), DateUtil.getZonedDateTime(), subjectTextField.getText(), entryTextArea.getText());
		chooseFileToSave(linesOfEntry);
		
		//Test SaveFile Window
//		List<String> linesOfEntry = Arrays.asList("Name: " + nameTextField.getText(), "Date: " + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterVerbose()), "Subject: " + subjectTextField.getText(), "Entry:", entryTextArea.getText());
//		log.setLog(nameTextField.getText(), DateUtil.getZonedDateTime(), subjectTextField.getText(), entryTextArea.getText());
//		printLogDummy();
//		if(!fileOrFolderOfAmiel.exists())
//		{
//			alert = new AlertUtil();
//			alert.runAlertMessage(new Alert(AlertType.WARNING), mainApp, title, headerText, contentText);
//		}
//		else
//		{
//			File file = new File("D:/Documents (D)/Daily Log/Entry_" + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterEntry()) + ".txt");
//			try
//			{
//				file.createNewFile();
//				Files.write(file.toPath(), linesOfEntry, Charset.forName("UTF-8"));
//				//			printToConsole.print(file);
//			}
//			catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
	}

	@FXML
	private void handleOnClickReleaseSendLog()
	{

	}
}
