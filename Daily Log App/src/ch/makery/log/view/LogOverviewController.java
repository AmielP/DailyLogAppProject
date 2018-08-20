package ch.makery.log.view;

//import java.beans.EventHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.makery.log.MainApp;
import ch.makery.log.model.Log;
import ch.makery.log.services.ISearchFileOrDirectory;
import ch.makery.log.services.FindMostRecentFile;
import ch.makery.log.services.ILogTemplate;
import ch.makery.log.services.IMatchContent;
import ch.makery.log.util.DateUtil;
import ch.makery.log.util.FindMostRecentTextFile;
import ch.makery.log.util.MatchLogContent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
	private String dailyLogPathDirectoryOfBCT = "C:";
	
	private String dailyLogPathTest = "D:/TestDailyLog";

	private String dailyLogPathOfAmiel;

	private String dailyLogPathOfBCT;
	
	private File fileOrFolderOfAmiel = new File(dailyLogPathDirectoryOfAmiel);
	
	private File fileOrFolderOfBCT = new File(dailyLogPathDirectoryOfBCT);

	//	private String newLine = System.getProperty("line.separator");

	public LogOverviewController()
	{

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

	private void readTextFile(String selectUserPath)
	{		
		try
		{
			byte[] buffer = new byte[1000];

			FileInputStream inputStream = new FileInputStream(selectUserPath);

			int nRead = 0;

			while((nRead = inputStream.read(buffer)) != -1){}

			//put match here
			nameTextField.setText(match.matchLogContent(buffer, "Name: ", "Date:"));
			subjectTextField.setText(match.matchLogContent(buffer, "Subject: ", "Entry:"));
			entryTextArea.setText(match.matchLogContent(buffer, "Entry:"));

			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("ERROR: Unable to open file, " + selectUserPath);
		}
		catch(IOException e)
		{
			System.out.println("ERROR: Unable to read file, " + selectUserPath);
		}
	}

	private void showLogDetails()
	{
		DateUtil.updateTime(dateLabel, DateUtil.getDatePattern(), DateUtil.getDateFormatter());
		DateUtil.displayTimer();
	}
	
	private void determineExistingPath()
	{
		if(!fileOrFolderOfAmiel.exists())
		{
			mostRecentTextFile = new FindMostRecentTextFile(dailyLogPathTest);
			
//			dailyLogPathOfBCT = findMostRecentTextFile.findFileOrFolder(dailyLogPathDirectoryOfBCT, fileExtension);
			readTextFile(dailyLogPathTest);
		}
		else
		{
			mostRecentTextFile = new FindMostRecentTextFile(dailyLogPathDirectoryOfAmiel);
//			dailyLogPathOfAmiel = findMostRecentTextFile.findFileOrFolder(dailyLogPathDirectoryOfAmiel, fileExtension);
			readTextFile(dailyLogPathOfAmiel);
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

	private void wrapTextArea(TextArea textArea)
	{
		textArea.setWrapText(true);
	}

	private void printLogDummy()
	{
		System.out.println(log.getName() + "\n" + log.getDate() + "\n" + log.getSubject() + "\n" + log.getEntry());
	}

	@FXML
	private void handleSendLog()
	{
		//		DateUtil.updateTime(dateLabel);
		List<String> linesOfEntry = Arrays.asList("Name: " + nameTextField.getText(), "Date: " + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterVerbose()), "Subject: " + subjectTextField.getText(), "Entry:", entryTextArea.getText());
		log.setLog(nameTextField.getText(), DateUtil.getZonedDateTime(), subjectTextField.getText(), entryTextArea.getText());
		printLogDummy();
		File file = new File("D:/Documents (D)/Daily Log/Entry_" + DateUtil.format(DateUtil.getZonedDateTime(), DateUtil.getDateFormatterEntry()) + ".txt");
		try
		{
			file.createNewFile();
			Files.write(file.toPath(), linesOfEntry, Charset.forName("UTF-8"));
			//			printToConsole.print(file);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void handleOnClickReleaseSendLog()
	{

	}
}
