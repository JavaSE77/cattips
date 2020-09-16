package xyz.javase.catTips;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Setup {
	
	/*
	 * Please format your setup file like this:
	 * 
	 * Line one, name of the profile: Cat Tips
	 * Sender email address: cattips@catlover.com
	 * Sender email password password1
	 * SMTP host: mail.smtp.host
	 * SMTP relay: smtp.mail.com
	 * UNIX time between automated messages: 86000
	 * */

	/**
	 * @return the 5 parms needed to open the email Handler
	 * */
	public static List<String> setupMethod(String loc) {
		List<String> list = new ArrayList<>();
		
	       BufferedReader br = null;
	       try{	
	           br = new BufferedReader(new FileReader(loc));		


		   System.out.println("Setting up\n");
		   String contentLine = br.readLine();
		   while (contentLine != null) {
			   list.add(contentLine);
		      contentLine = br.readLine();
		   }
	       }
	       catch (IOException ioe) 
	       {
		   ioe.printStackTrace();
	       } 
	       finally 
	       {
		   try {
		      if (br != null)
			 br.close();
		   } 
		   catch (IOException ioe) 
	           {
			System.out.println("Error in closing the BufferedReader");
		   }
		}
	       return list;
	}
	
}
