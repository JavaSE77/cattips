package xyz.javase.catTips.subcribers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Subscriber {
	
	private Long date = System.currentTimeMillis();
	private int hour = 3600;
	private String flname;
	private String subAddress;
	private List<String> fileStruct;

	/**
	 * String name is the name of the subscribers file
	 * String address is the address, including the @ will be sent the message
	 * */
	public Subscriber(String fileName, String address) {
		flname = fileName;
		subAddress = address;
		fileStruct = fillArray();
	}
	
	public boolean isInitialized() {
		if(fileStruct.get(0) != null)
			return true;
		
		return false;
	}
	
	public long getLastSent() {
		Long lastSent = (long) 0;
		
		return lastSent;
	}
	
	private List<String> fillArray() {
		List<String> list = new ArrayList<>();
		
	       BufferedReader br = null;
	       try{	
	           br = new BufferedReader(new FileReader(flname));		


		   System.out.println("Reading tip: " + flname);
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
	public void updateSubscriberInfo() {
		
	}
	
	/**
	 * Getter method
	 * @return returns fileName string
	 * */
	public String fileName() {
		return flname;
	}
	
	/**
	 * Setter method
	 * @param takes sub address 
	 * Par1 is the before @ part of the address
	 * par2 is the after @ part of the address
	 * */
	public void setAddress(String par1, String par2) {
		subAddress = par1 + "@" + par2;
		fileStruct.set(0, par1);
		fileStruct.set(1, par2);
		
		updateSubscriberInfo();

	}
	
	/**
	 * Checks to see if the user is listed as active in the file struct
	 * @return returns boolean 
	 * */
	public boolean checkActiveStatus() {
		
		String l3 = fileStruct.get(2);
		
		if(l3.equalsIgnoreCase("active=true"))
			return true;
		
		return false;
	}
	
	/**
	 * Setter method
	 * @param takes boolean value
	 * */
	public void setActive(boolean val) {
		
		if(val ==  true) {
			fileStruct.set(2, "active=true");
		} else {
			fileStruct.set(2, "active=false");
		}
		updateSubscriberInfo();
	}
}
