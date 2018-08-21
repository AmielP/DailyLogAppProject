package ch.makery.log;

import java.io.IOException;

import ch.makery.log.model.Log;
import ch.makery.log.util.DateUtil;
import ch.makery.log.view.LogOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application 
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Label dateLabel = new Label("message1");
	private ObservableList<Log> logData = FXCollections.observableArrayList();
	private Text text = new Text();
	
	public MainApp()
	{
		logData.add(new Log("Johnny Valentino"));
		logData.add(new Log("Rudolph Reed"));
		logData.add(new Log("Lou Vixen"));
		logData.add(new Log("Vivien Leigh"));
		logData.add(new Log("Nicole Stanford"));
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
		text.setFont(Font.loadFont("file:resources/fonts/PIXEL DIGIVOLVE.OTF", 120));
		
		initRootLayout();
		
		showLogOverview();
	}
	
	//Initialize the root layout
	public void initRootLayout()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane)loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(IOException e)
		{
			e.printStackTrace();
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
			
			LogOverviewController controller = loader.getController();
			controller.setMainApp(this);;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
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
