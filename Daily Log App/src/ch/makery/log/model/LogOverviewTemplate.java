package ch.makery.log.model;

import java.io.File;
import java.io.FileInputStream;

public abstract class LogOverviewTemplate 
{
	private InputFile sourceFile;
	private OutputFile destinationFile;
	private PrimaryStage primStage;
	private FileInputStream logOverviewStream;
	private byte[] logOverviewContent;
	
	public void setSourceFile(InputFile sourceFile)
	{
		this.sourceFile = sourceFile;
	}
	
	//Remove if not needed
	public InputFile getSourceFile()
	{
		return sourceFile;
	}
	
	public void setDestinationFile(OutputFile destinationFile)
	{
		this.destinationFile = destinationFile;
	}
	
	//Remove if not needed
	public OutputFile getDestinationFile()
	{
		return destinationFile;
	}
	
	public void setLogOverviewStream(FileInputStream logOverviewStream)
	{
		this.logOverviewStream = logOverviewStream;
	}
	
	public FileInputStream getLogOverviewStream()
	{
		return logOverviewStream;
	}
	
	public void setLogOverviewContent(byte[] logOverviewContent)
	{
		this.logOverviewContent = logOverviewContent;
	}
	
	public byte[] getLogOverviewContent()
	{
		return logOverviewContent;
	}
	
	public void setPrimStage(PrimaryStage primStage)
	{
		this.primStage = primStage;
	}
	
	public PrimaryStage getPrimStage()
	{
		return primStage;
	}
	
	//MOVE TO DIFFERENT CLASS AND THEN DELETE
//	public abstract void setLogFilePath(File file);
//	
//	public abstract File getLogFilePath();
	
	public abstract void readTextFile(Object selectUserPath);
}
