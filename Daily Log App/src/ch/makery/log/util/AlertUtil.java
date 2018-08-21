package ch.makery.log.util;

import ch.makery.log.MainApp;
import ch.makery.log.services.IAlert;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil implements IAlert
{
	@Override
	public void runAlertMessage(Object app, String title, String headerText, String contentText)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(((MainApp) app).getPrimaryStage());
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);

		alert.showAndWait();
	}
}
