package com.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	
	/* Function Name: createResultFolder
	 * Description: This method will call before the test execution and it will verify whether the results folder exists in the current test directory
	 * 
	 * Author: Kowshik
	 * Date: 10th Feb 2018
	 * 
	 * Reviewer:
	 * 
	 * Comments:
	 * 
	 */
	
	public static void createResultFolder()
	{
		// Get the system test directory path
		
		String crntdir = System.getProperty("user.dir");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		Date d = new Date();
		//sdf.format(d);
		
		// Format the date into DD-MM-YYYY
		System.out.println(sdf.format(d));
		System.out.println(crntdir);
		
		File f = new File(crntdir+"\\Results\\"+sdf.format(d));
		
		// Verify and create results folder
		
		if(!f.exists())
		{
			f.mkdirs();
		}
	}

}
