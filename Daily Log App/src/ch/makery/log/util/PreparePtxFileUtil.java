package ch.makery.log.util;

import static jcuda.driver.JCudaDriver.cuCtxCreate;
import static jcuda.driver.JCudaDriver.cuDeviceGet;
import static jcuda.driver.JCudaDriver.cuInit;
import static jcuda.driver.JCudaDriver.cuModuleGetFunction;
import static jcuda.driver.JCudaDriver.cuModuleLoad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.prefs.Preferences;

import jcuda.driver.CUcontext;
import jcuda.driver.CUdevice;
import jcuda.driver.CUfunction;
import jcuda.driver.CUmodule;
import jcuda.driver.JCudaDriver;
import ch.makery.log.services.IFileIO;
import ch.makery.log.services.IPrepareFile;

public class PreparePtxFileUtil implements IPrepareFile, IFileIO
{	
	private File cuFile;
	private Object function;
	
	public void setupFileForCUDA(String ptxFile, String kernelFunctionName) throws IOException
	{	
		JCudaDriver.setExceptionsEnabled(true);
		
		String ptxFileName = prepareFile(ptxFile/*"JCudaVectorAddKernel.cu"*/);
		
		cuInit(0);
		CUdevice device = new CUdevice();
		cuDeviceGet(device, 0);
		CUcontext context = new CUcontext();
		cuCtxCreate(context, 0, device);
		
		CUmodule module = new CUmodule();
		cuModuleLoad(module, ptxFileName);
		
		cuModuleGetFunction((CUfunction)function, module, kernelFunctionName);
	}
	
	@Override
	public String prepareFile(String cuFileName) throws IOException
	{
		int endIndex = cuFileName.lastIndexOf('.');
		if(endIndex == -1)
		{
			endIndex = cuFileName.length() - 1;
		}
		
		String ptxFileName = cuFileName.substring(0, endIndex + 1) + "ptx";
		
		File ptxFile = new File(ptxFileName);
		if(ptxFile.exists())
		{
			return ptxFileName;
		}
		
		cuFile = new File("src/" + cuFileName);
		if(!cuFile.exists())
		{
			throw new IOException("Input file not found: " + cuFileName);
		}
		
		String modelString = "-m" + System.getProperty("sun.arch.data.model");
		String command = "nvcc " + modelString + " -ptx " +
				cuFile.getPath() + " -o " + ptxFileName;
		
		System.out.println("Executing\n" + command);
		Process process = Runtime.getRuntime().exec(command);
		
		String errorMessage =
				new String(toByteArray(process.getErrorStream()));
		String outputMessage =
				new String(toByteArray(process.getInputStream()));
		int exitValue = 0;
		try
		{
			exitValue = process.waitFor();
		}
		catch(InterruptedException e)
		{
			Thread.currentThread().interrupt();
			throw new IOException("Interrupted while waiting for nvcc output", e);
		}
		
		if(exitValue != 0)
		{
			System.out.println("nvcc process exitValue " + exitValue);
			System.out.println("errorMessage:\n" + errorMessage);
			System.out.println("outputMessage:\n" + outputMessage);
			throw new IOException("Could not create .ptx file: " + errorMessage);
		}
		
		System.out.println("Finished creating PTX file");
		return ptxFileName;
	}
	
	private static byte[] toByteArray(InputStream inputStream) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		while(true)
		{
			int read = inputStream.read(buffer);
			if(read == -1)
			{
				break;
			}
			baos.write(buffer, 0, read);
		}
		return baos.toByteArray();
	}

	@Override
	public File getFile() 
	{
		return cuFile;
	}

	@Override
	public void setFile(File cuFile) 
	{
		this.cuFile = cuFile;
	}
	
	@Override
	public void setFunction(Object function)
	{
		this.function = function;
	}
	
	@Override
	public Object getFunction()
	{
		return function;
	}

//	@Override
//	public void readI(Object readObject, String path)
//	{
//		//TODO NOTHING
//	}

	@Override
	public void writeO() 
	{
		//TODO NOTHING
	}

	@Override
	public void setPreferences(Preferences preferences) {}

	@Override
	public Preferences getPreferences() {return null;}

	@Override
	public void saveFile(List<Object> objectList, File file) 
	{
		// TODO Nothing	
	}
}
