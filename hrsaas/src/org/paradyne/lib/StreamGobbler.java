package org.paradyne.lib;
import java.util.*;
import java.io.*;
class StreamGobbler 
{
	InputStream is;
	String type;
	OutputStream os;
	
	StreamGobbler(InputStream is, String type)
	{
		this(is, type, null);
	}
	StreamGobbler(InputStream is, String type, OutputStream redirect)
	{
		this.is = is;
		this.type = type;
		this.os = redirect;
	}
	
	public String exec()
	{
		String name="";
		try
		{
			PrintWriter pw = null;
			if (os != null)
				pw = new PrintWriter(os);
				
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line=null;
			int i=0;
			while ( (line = br.readLine()) != null)
			{
				i++;
				if (pw != null)
					pw.println(line);
					
						name+=line;
					
					
			}
			if (pw != null)
				pw.flush();
		} catch (IOException ioe)
			{
			ioe.printStackTrace();  
			}
		return name;
	}
}