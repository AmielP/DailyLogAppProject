package ch.makery.log;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.log.model.Log;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.model.SaveAndOpenFileOption;
import ch.makery.log.util.SavingUserPreferences;
import ch.makery.log.view.LogOverviewController;
import ch.makery.log.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
//import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application //ALL COMMENTED BLOCKS OF CODE REGARDING ARCHAIC SAVING CAN BE DELETED IN THE FUTURE AS SOON AS MODERN SAVING IS IMPLEMENTED
{
	private Stage primaryStage;
	private BorderPane rootLayout;
//	private Scene scene;
	private LogOverviewController logOverviewController;
	private SaveAndOpenFileOption saveFile;
	private SavingUserPreferences savingUserPreferences;
	//	private Label dateLabel = new Label("message1");
	private ObservableList<Log> logData = FXCollections.observableArrayList();

	public MainApp()
	{
		logData.add(new Log("Johnny Valentino"));
		logData.add(new Log("Rudolph Reed"));
		logData.add(new Log("Lou Vixen"));
		logData.add(new Log("Vivien Leigh"));
		logData.add(new Log("Nicole Standford"));
	}

	public ObservableList<Log> getLogData()
	{
		return logData;
	}

	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Daily Log App");

		initRootLayout();

		showLogOverview();
	}

	//Initialize the root layout
	public void initRootLayout()
	{
		saveFile = new SaveAndOpenFileOption();
		savingUserPreferences = new SavingUserPreferences();
		
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane)loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
		
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(280);
			
			RootLayoutController rootLayoutController = loader.getController();
			rootLayoutController.setMainApp(this);
			primaryStage.show();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		saveFile.setFile(getLogFilePath());
		if(saveFile.getFile() != null)
		{
			loadLogDataFromFile(saveFile.getFile());
		}
	}

	//Shows the log overview inside the root layout
	public void showLogOverview()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LogOverview.fxml"));
			AnchorPane logOverview = (AnchorPane)loader.load();

			rootLayout.setCenter(logOverview);

			LogOverviewController template = loader.getController();
			
			logOverviewController = template;
			
			//Delete this later when I finish FXML eventHandler methods in RootLayoutController()
//			scene.setOnKeyPressed(e -> 
//			{
//				if((e.isShortcutDown() && e.isShiftDown() && e.getCode() == KeyCode.S) || (e.isShortcutDown() && e.getCode() == KeyCode.S))
//				{
//					logOverviewController.handleSave();
//				}
//				else if(e.isShortcutDown() && e.getCode() == KeyCode.Q)
//				{
//					logOverviewController.handleExit();
//				}
//			});
			
			template.setMainApp(this);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void setPrimaryStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}

	//Returns the main stage
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
	
	public LogOverviewController getLogOverviewController()
	{
		return logOverviewController;
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public void loadLogDataFromFile(File file)
	{
		try
		{
			JAXBContext context = JAXBContext
	                .newInstance(LogListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        LogListWrapper wrapper = (LogListWrapper) um.unmarshal(file);

	        logData.clear();
	        logData.addAll(wrapper.getLogs());

	        // Save the file path to the registry.
	        setLogFilePath(file);

	    } 
		catch (Exception e) 
		{ 
			// catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
	
	public void saveLogDataToFile(File file) 
	{
	    try 
	    {
	        JAXBContext context = JAXBContext
	                .newInstance(LogListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        LogListWrapper wrapper = new LogListWrapper();
	        wrapper.setLogs(logData);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	        // Save the file path to the registry.
	        setLogFilePath(file);
	    } 
	    catch (Exception e) 
	    { 
	    	// catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
	
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getLogFilePath() 
	{
	    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	    String filePath = prefs.get("filePath", null);
	    if (filePath != null) 
	    {
	        return new File(filePath);
	    } 
	    else 
	    {
	        return null;
	    }
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public void setLogFilePath(File file) 
	{
	    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	    if (file != null) 
	    {
	        prefs.put("filePath", file.getPath());

	        // Update the stage title.
	        primaryStage.setTitle("Daily Log App - " + file.getName());
	    } 
	    else 
	    {
	        prefs.remove("filePath");

	        // Update the stage title.
	        primaryStage.setTitle("Daily Log App");
	    }
	}
}