package xyz.javase.catTips.subcribers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Setup {
	
	/**
	 * @return the subscriber array in accordance to the file.
	 * */
	public static List<String> getSubInfoFromFile(String flname) {
		List<String> list = new ArrayList<>();
		
	       BufferedReader br = null;
	       try{	
	           br = new BufferedReader(new FileReader(flname));		


		   System.out.println("getting info from: " + flname + "\n");
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
	
	/**
	 * Buffered file writer writes contents of arraylist into subscriber file.
	 * */
	public static void updateSubscriberInfo(String flname, List<String> struc) {
        try {
            
           
  
            File file = new File(flname);
 
            if (!file.exists()) {
                file.createNewFile();
            }
  
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i = 0; i < struc.size(); i++) {
            	bw.write(struc.get(i));
            }
            
            
            bw.close();
  
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
}
