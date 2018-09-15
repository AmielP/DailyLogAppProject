package ch.makery.log.model;

import ch.makery.log.services.AccessGUIElements;
import javafx.stage.Stage;

public class PrimaryStage extends AccessGUIElements
{
	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}
	
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
}
