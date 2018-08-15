package ch.makery.log.services;

import java.time.ZonedDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public interface ILogTemplate 
{	
	void setName(String name);
	String getName();
	StringProperty nameProperty();
	void setDate(ZonedDateTime date);
	ZonedDateTime getDate();
	ObjectProperty<ZonedDateTime> dateProperty();
	void setSubject(String subject);
	String getSubject();
	StringProperty subjectProperty();
	void setEntry(String entry);
	String getEntry();
	StringProperty entryProperty();
	void setLog(String name, ZonedDateTime zonedDateTime, String subject, String entry);
}
