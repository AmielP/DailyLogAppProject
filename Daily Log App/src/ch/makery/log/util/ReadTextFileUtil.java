package ch.makery.log.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ch.makery.log.model.LogOverviewTemplate;

public class ReadTextFileUtil extends LogOverviewTemplate
{
	private FileInputStream fileInputStream;
	private byte[] textFileContent;
	
	public ReadTextFileUtil()
	{
		setSourceFile(new InputFile());
		getSourceFile().setBuffer(new byte[10000]);
	}

	@Override
	public void readTextFile(String selectUserPath) 
	{
//		byte[] buffer = new byte[100000];
		
		try 
		{
			getSourceFile().setReadObject(new FileInputStream(selectUserPath));
			fileInputStream = (FileInputStream) getSourceFile().getReadObject();
			textFileContent = getSourceFile().getBuffer();
			setLogOverviewStream(fileInputStream);
			setLogOverviewContent(textFileContent);
			
			getSourceFile().readI(new FileInputStream(selectUserPath), selectUserPath);
			
			//Put something in relation to reading the file by byte streams here????
			int nRead = 0;
			
			
			while((nRead = (fileInputStream).read(textFileContent)) != -1){}

//			nameTextField.setText(match.matchLogContent(buffer, "Name: ", "Date:"));
//			subjectTextField.setText(match.matchLogContent(buffer, "Subject: ", "Entry:"));
//			entryTextArea.setText(match.matchLogContent(buffer, "Entry:"));
//
//			((FileInputStream) fileInputStream).close();
			
			//inpu
		} 
		catch(FileNotFoundException e)
		{
			System.out.println("ERROR: Unable to open file, " + selectUserPath);
		}
		catch(IOException e)
		{
			System.out.println("ERROR: Unable to read file, " + selectUserPath);
		}

//		int nRead = 0;
//
//		while((nRead = inputStream.read(buffer)) != -1){}
//
//		nameTextField.setText(match.matchLogContent(buffer, "Name: ", "Date:"));
//		subjectTextField.setText(match.matchLogContent(buffer, "Subject: ", "Entry:"));
//		entryTextArea.setText(match.matchLogContent(buffer, "Entry:"));
//
//		inputStream.close();
	}
}
