package xyz.javase.catTips.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class FileHandler {

	public static String generateCatTip() {
		
		String cwd = System.getProperty("user.dir");
		File tipsDirectory = new File(cwd + File.separator + "tips");
		
		File tipsList[] = tipsDirectory.listFiles();
		
		Random random = new Random();
		int rand = random.nextInt(tipsList.length);
		System.out.println("Randomly chosen tip " + rand);
		
		return getTip(tipsList[rand].getPath());
	}
	
	public static String appendStopMessage(String input) {
		String stopMsg = "Reply \"STOP\" to stop future messages";
		
		return input + stopMsg;
	}
	
	private static String getTip(String path) {
		
		String tip = "";
		
	       BufferedReader br = null;
	       try{	
	           br = new BufferedReader(new FileReader(path));		


		   System.out.println("Reading tip: " + path);
		   String contentLine = br.readLine();
		   while (contentLine != null) {
			   tip = tip + contentLine + "\n";
		      contentLine = br.readLine();
		   }
		      System.out.println(tip);
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
	return tip;	
	}
	
}

