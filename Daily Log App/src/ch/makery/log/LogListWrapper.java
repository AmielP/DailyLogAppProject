package ch.makery.log;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.makery.log.model.Log;

@XmlRootElement(name = "logs")
public class LogListWrapper 
{
	private List<Log> logs;
	
	@XmlElement(name = "log")
	public List<Log> getLogs()
	{
		return logs;
	}
	
	public void setLogs(List<Log> logs)
	{
		this.logs = logs;
	}
}
