package ch.makery.log.model;

import java.io.File;
import java.io.IOException;

import ch.makery.log.services.IFileIO;
import ch.makery.log.services.IPrepareFile;

public class InputFile implements IFileIO, IPrepareFile
{
	private byte[] buffer;
	private Object readObject;
	private File file;
	private String pathOfFile;
	
//	@Override
//	public void readI(Object readObject, String path)
//	{		
////		try
////		{
////			byte[] buffer = new byte[100000];
//
//			this.readObject = readObject;
//
////			int nRead = 0;
////
////			while((nRead = inputStream.read(buffer)) != -1){}
////
////			nameTextField.setText(match.matchLogContent(buffer, "Name: ", "Date:"));
////			subjectTextField.setText(match.matchLogContent(buffer, "Subject: ", "Entry:"));
////			entryTextArea.setText(match.matchLogContent(buffer, "Entry:"));
////
////			inputStream.close();
////		}
////		catch(FileNotFoundException e)
////		{
////			System.out.println("ERROR: Unable to open file, " + path);
////		}
////		catch(IOException e)
////		{
////			System.out.println("ERROR: Unable to read file, " + path);
////		}
//	}
	
	@Override
	public void writeO() {}
	
	@Override
	public void setFile(File file) 
	{
		this.file = file;
	}

	@Override
	public File getFile() 
	{
		return file;
	}
	
	@Override
	public String prepareFile(String fileName) throws IOException 
	{
		return null;
	}
	
	@Override
	public void setFunction(Object readObject) 
	{
		this.readObject = readObject;
	}

	@Override
	public Object getFunction() 
	{
		return readObject;
	}
	
	public void setBuffer(byte[] buffer)
	{
		this.buffer = buffer;
	}
	
	public byte[] getBuffer()
	{
		return buffer;
	}
	
	public void setPathOfFile(String pathOfFile)
	{
		this.pathOfFile = pathOfFile;
	}
	
	public String getPathOfFile()
	{
		return pathOfFile;
	}
//	
//	public void setReadObject(Object readObject)
//	{
//		this.readObject = readObject;
//	}
//	
//	public Object getReadObject()
//	{
//		return readObject;
//	}
	//Reference so Delete when finished with actual read method
//	private void readTextFile(String selectUserPath)
//	{		
//		try
//		{
//			byte[] buffer = new byte[100000];
//
//			FileInputStream inputStream = new FileInputStream(selectUserPath);
//
//			int nRead = 0;
//
//			while((nRead = inputStream.read(buffer)) != -1){}
//
//			//put match here
//			nameTextField.setText(match.matchLogContent(buffer, "Name: ", "Date:"));
//			subjectTextField.setText(match.matchLogContent(buffer, "Subject: ", "Entry:"));
//			entryTextArea.setText(match.matchLogContent(buffer, "Entry:"));
//
//			inputStream.close();
//		}
//		catch(FileNotFoundException e)
//		{
//			System.out.println("ERROR: Unable to open file, " + selectUserPath);
//		}
//		catch(IOException e)
//		{
//			System.out.println("ERROR: Unable to read file, " + selectUserPath);
//		}
//	}
}
