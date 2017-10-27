package com.example.shining.p044_wechat_record.greendaoset;



import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShellExeUtil {

	/*
	 * how to use try { execCommand("./data/kenshin/x.sh"); } 
	 * catch (IOException e) { e.printStackTrace(); } }
	 */
	public static String ERROR = "ERROR";
	private static StringBuilder sb = new StringBuilder("");
	public static String getOutput()
	{
		return sb.toString();
	}
	public static int execCommand(String[] command) throws IOException, OutOfMemoryError {

		// start the ls command running
		// String[] args = new String[]{"sh", "-c", command};
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec(command); 
		InputStream inputstream = proc.getInputStream();
		InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
		BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

		// read the ls output		
		//sb = new StringBuilder("");
		sb.delete(0, sb.length());
		try {
			if (proc.waitFor() != 0) {
				Log.i("MTK","exit value = " + proc.exitValue());
				sb.append(ERROR + proc.exitValue());
				return -1;
			}
			else
			{	
				String line;	
				//one line has not CR, or  "line1 CR line2 CR line3..."				
				line = bufferedreader.readLine();
				if(line != null)
				{
					sb.append(line);
				}
				else
				{
					return 0;
				}
				while(true)
				{
					line = bufferedreader.readLine();
					if(line == null)
					{
						break;
					}
					else
					{
						sb.append('\n');
						sb.append(line);
					}		
				}
				return 0;
			}
		} catch (InterruptedException e) {
			Log.i("MTK","exe fail " + e.toString());
			sb.append(ERROR + e.toString());
			return -1;
		}
	}
}
