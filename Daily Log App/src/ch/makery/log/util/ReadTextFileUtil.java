package ch.makery.log.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ch.makery.log.MainApp;
import ch.makery.log.model.InputFile;
import ch.makery.log.model.LogOverviewTemplate;
import ch.makery.log.services.IPrepareFile;
import jcuda.driver.CUfunction;

public class ReadTextFileUtil extends LogOverviewTemplate
{
	private FileInputStream fileInputStream;
	private byte[] textFileContent;
	private IPrepareFile preparePtxFileUtil;
	private File textFile;
	
	public ReadTextFileUtil()
	{
		preparePtxFileUtil = new PreparePtxFileUtil();
		preparePtxFileUtil.setFunction(new CUfunction());
		setSourceFile(new InputFile());
	}
	
	private void assignTextFileToRead(Object textFileName)
	{
		getSourceFile().setFile(new File((String)textFileName));
	}
	
	private void calculateBufferSizeFromFile(File textFile)
	{
		getSourceFile().setBuffer(new byte[(int) textFile.length()]);
	}
	
	private void assignTextFileInputStream(Object textFileName) throws FileNotFoundException
	{
		getSourceFile().setFunction(new FileInputStream((String)textFileName));
	}
	
	public void readTextFile(Object selectUserPath)
	{
		assignTextFileToRead(selectUserPath);
		textFile = getSourceFile().getFile();
		try
		{
			assignTextFileInputStream(selectUserPath);
			fileInputStream = (FileInputStream)getSourceFile().getFunction();
			calculateBufferSizeFromFile(textFile);

			textFileContent = getSourceFile().getBuffer();
			setLogOverviewStream(fileInputStream);
			setLogOverviewContent(textFileContent);
			
			int nRead = 0;
			
			while((nRead = fileInputStream.read(textFileContent)) != -1) {}
			
			fileInputStream.close();
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: Unable to open file, " + selectUserPath);
		}
		catch(IOException e)
		{
			System.out.println("ERROR: Unable to read file, " + selectUserPath);
		}
	}
	
	public FileInputStream getFileInputStream()
	{
		return fileInputStream;
	}

	@Override
	public void chooseFileToSave(List<Object> objectList, File file) 
	{
		// TODO Nothing
	}
}
