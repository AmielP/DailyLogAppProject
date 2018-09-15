package ch.makery.log.services;

import ch.makery.log.MainApp;

public abstract class AccessGUIElements 
{
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) 
	{
		this.mainApp = mainApp;
	}
	public MainApp getMainApp()
	{
		return mainApp;
	}
}
