package ch.makery.log;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.log.model.Log;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.model.SaveAndOpenFileOption;
import ch.makery.log.util.SavingUserPreferences;
import ch.makery.log.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application //ALL COMMENTED BLOCKS OF CODE REGARDING ARCHAIC SAVING CAN BE DELETED IN THE FUTURE AS SOON AS MODERN SAVING IS IMPLEMENTED
{
	private Stage primaryStage;
	private BorderPane rootLayout;
//	private SaveAndOpenFileOption saveFile;
//	private SavingUserPreferences savingUserPreferences;
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
	
//	public void loadLogDataFromFile(File file)
//	{
//		savingUserPreferences = new SavingUserPreferences();
//		
//		try
//		{
//			JAXBContext context = JAXBContext.newInstance(LogListWrapper.class);
//			Unmarshaller unmarshaller = context.createUnmarshaller();
//			
//			LogListWrapper logListWrapper = (LogListWrapper) unmarshaller.unmarshal(file);
//			
//			logData.clear();
//			logData.addAll(logListWrapper.getLogs());
//			
//			savingUserPreferences.setLogFilePath(file);
//		}
//		catch(Exception e)
//		{
//			Alert alert = new Alert(AlertType.ERROR);
//	        alert.setTitle("Error");
//	        alert.setHeaderText("Could not load data");
//	        alert.setContentText("Could not load data from file:\n" + file.getPath());
//
//	        alert.showAndWait();
//	    }
//	}
	
//	public void saveLogDataToFile(File file) 
//	{
//		savingUserPreferences = new SavingUserPreferences();
//		
//	    try 
//	    {
//	        JAXBContext context = JAXBContext
//	                .newInstance(LogListWrapper.class);
//	        Marshaller m = context.createMarshaller();
//	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//	        // Wrapping our person data.
//	        LogListWrapper wrapper = new LogListWrapper();
//	        wrapper.setLogs(logData);
//
//	        // Marshalling and saving XML to the file.
//	        m.marshal(wrapper, file);
//
//	        // Save the file path to the registry.
//	        savingUserPreferences.setLogFilePath(file);
//	    } 
//	    catch (Exception e) 
//	    { 
//	    	// catches ANY exception
//	        Alert alert = new Alert(AlertType.ERROR);
//	        alert.setTitle("Error");
//	        alert.setHeaderText("Could not save data");
//	        alert.setContentText("Could not save data to file:\n" + file.getPath());
//
//	        alert.showAndWait();
//	    }
//	}

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
//		saveFile = new SaveAndOpenFileOption();
//		savingUserPreferences = new SavingUserPreferences();
		
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane)loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(280);
			
			RootLayoutController rootLayoutController = loader.getController();
			rootLayoutController.setMainApp(this);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
//		saveFile.setFile(savingUserPreferences.getLogFilePath());
//		if(saveFile.getFile() != null)
//		{
//			loadLogDataFromFile(saveFile.getFile());
//		}
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

			LogOverviewTemplate template = loader.getController();
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

	public static void main(String[] args) 
	{
		launch(args);
	}
}