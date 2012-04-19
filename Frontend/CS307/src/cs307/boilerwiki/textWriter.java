package cs307.boilerwiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;

public class textWriter extends Activity{

	public static final String FILENAME = "availableFiles.txt";
	
	//Removes a file with the given name
	public boolean deleteFile(String filename)
	{
		File theFile = new File(getFilesDir()+"/"+filename);
		boolean checkDelete = theFile.delete();
		
		checkDelete = checkDelete && removeDesc(FILENAME,filename);
		
		return checkDelete;		
	}
	
	//Removes a description from the main desc file, returns true if successful, false otherwise
	public boolean removeDesc(String filename,String text)
	{
		ArrayList<String> theList = new ArrayList<String>();

		try{
			InputStream instream = openFileInput(filename);
			InputStreamReader inputreader = new InputStreamReader(instream);
		    BufferedReader buffreader = new BufferedReader(inputreader);
		    
		    String line;
		    while (null != (line = buffreader.readLine()))
		    {
		    	System.out.println(line);
		    	theList.add(line);
		    }
				
			instream.close();
		}catch(Exception e){}
		int index = theList.indexOf(text);
		
		if(index < 0)
			return false;
		theList.remove(theList.indexOf(text));
		
		try{
			FileOutputStream fos = openFileOutput(filename,Context.MODE_PRIVATE);
			
			for(int i = 0; i < theList.size(); i ++)
			{
				fos.write((theList.get(i)+"\n").getBytes());
			}
			
			fos.close();
			
		}catch(Exception e){return false;}
		
		return true;
	}
	
	
	//Adds the string text to the main desc file, returns true if successful, false otherwise
	public boolean updateDesc(String filename, String text)
	{
		try{
			FileOutputStream fos = openFileOutput(filename, Context.MODE_APPEND);
			fos.write(text.getBytes());
			fos.flush();
			fos.close();
		}catch(Exception e){return false;}
		return true;
	}
	
	//Reads from a file and returns an arraylist of strings
	public ArrayList<String> readDesc(String filename)
	{
		ArrayList<String> theList = new ArrayList<String>();
	
		
		try{
			InputStream instream = openFileInput(filename);
			InputStreamReader inputreader = new InputStreamReader(instream);
		    BufferedReader buffreader = new BufferedReader(inputreader);
		    
		    String line;
		    while (null != (line = buffreader.readLine()))
		    {
		    	//System.out.println(line);
		    	theList.add(line);
		    }
				
			instream.close();
		}catch(Exception e){}
		
		return theList;
	}
	
	//Writes the string text to a file called filename, returns true if successful and false otherwise
	public boolean writeToFile(String filename,String text)
	{
		File file = getFileStreamPath(filename);
		if(!file.exists())
			updateDesc(FILENAME,filename);
		
		try{
			FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(text.getBytes());
			fos.flush();
			fos.close();
		}catch(Exception e){return false;}
		return true;
	}
	
}
