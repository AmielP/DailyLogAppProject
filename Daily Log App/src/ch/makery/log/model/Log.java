package ch.makery.log.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import ch.makery.log.services.ILogTemplate;
//import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Log implements ILogTemplate
{
	private final StringProperty name;
	private final ObjectProperty<ZonedDateTime> date;
	private final StringProperty subject;
	private final StringProperty entry;
	
	public Log()
	{
		this(null);
	}
	
	public Log(String name)
	{
		this.name = new SimpleStringProperty(name);
		
		this.date = new SimpleObjectProperty<ZonedDateTime>(ZonedDateTime.now());
		this.subject = new SimpleStringProperty("My Birthday Surprise");
		this.entry = new SimpleStringProperty("My brother got me a big pinata to eat.");
	}
	
	@Override
	public void setName(String name)
	{
		this.name.set(name);
	}
	
	@Override
	public String getName()
	{
		return name.get();
	}
	
	@Override
	public StringProperty nameProperty()
	{
		return name;
	}
	
	@Override
	public void setDate(ZonedDateTime date)
	{
		this.date.set(date);
	}
	
	@Override
	public ZonedDateTime getDate()
	{
		return date.get();
	}
	
	@Override
	public ObjectProperty<ZonedDateTime> dateProperty()
	{
		return date;
	}
	
	@Override
	public void setSubject(String subject)
	{
		this.subject.set(subject);
	}
	
	@Override
	public String getSubject()
	{
		return subject.get();
	}
	
	@Override
	public StringProperty subjectProperty()
	{
		return subject;
	}
	
	@Override
	public void setEntry(String entry)
	{
		this.entry.set(entry);
	}
	
	@Override
	public String getEntry()
	{
		return entry.get();
	}
	
	@Override
	public StringProperty entryProperty()
	{
		return entry;
	}
	
	@Override
	public void setLog(String name, ZonedDateTime zonedDateTime, String subject, String entry)
	{
		setName(name);
		setDate(zonedDateTime);
		setSubject(subject);
		setEntry(entry);
	}
}
