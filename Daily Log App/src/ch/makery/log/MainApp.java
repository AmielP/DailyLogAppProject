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
}