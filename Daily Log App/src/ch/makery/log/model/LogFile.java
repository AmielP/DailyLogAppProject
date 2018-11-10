package ch.makery.log.model;

import java.time.ZonedDateTime;

import ch.makery.log.services.ILogTemplate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class LogFile implements ILogTemplate
{
	public LogFile() {}

	@Override
	public void setName(String name) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringProperty nameProperty() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDate(ZonedDateTime date) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public ZonedDateTime getDate() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectProperty<ZonedDateTime> dateProperty() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSubject(String subject) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSubject() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringProperty subjectProperty() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntry(String entry) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEntry()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringProperty entryProperty()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLog(String name, ZonedDateTime zonedDateTime, String subject, String entry)
	{
		// TODO Auto-generated method stub
		
	}
	
}
