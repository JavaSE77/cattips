package xyz.javase.catTips.subcribers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Subscriber {
	

	private String flname;
	private List<String> fileStruct;

	/**
	 * String name is the name of the subscribers file
	 * String address is the address, including the @ will be sent the message
	 * */
	public Subscriber(String fileName) {
		flname = fileName;
		fileStruct = Setup.getSubInfoFromFile(flname);
	}
	
	/**
	 * Checks the struct to see if there is any data in it
	 * @return boolean value
	 * */
	public boolean isInitialized() {
		if(fileStruct.get(0) != null)
			return true;
		
		return false;
	}
	
	
	/**
	 * fetches email from struct
	 * @return String with email address
	 * */
	public String getAddress() {
		return fileStruct.get(0) + "@" + fileStruct.get(1);
	}
	
	
	/**
	 * Gets the unix time stamp of the last time an email was sent to the user
	 * @return long
	 * */
	public long getLastSent() {
		Long lastSent = (long) Long.parseLong(fileStruct.get(3));
		
		return lastSent;
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
		fileStruct.set(0, par1);
		fileStruct.set(1, par2);
		
		Setup.updateSubscriberInfo(flname, fileStruct);

	}
	
	/**
	 * Checks to see if the user is listed as active in the file struct
	 * @return returns boolean 
	 * */
	public boolean checkActiveStatus() {
		
		String l2 = fileStruct.get(2);
		
		if(l2.equalsIgnoreCase("active=true"))
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
		Setup.updateSubscriberInfo(flname, fileStruct);
	}
	
	
	/**
	 * Sets the current time as the last sent time
	 * set this parm to -1 for the current time. 0 to send a tip on the scheduled run
	 * */
	public void setLastSent(Long unixTime) {
		if(unixTime < 0) {
			unixTime = System.currentTimeMillis();
		}
		
		fileStruct.set(3, ("" +unixTime));
		Setup.updateSubscriberInfo(flname, fileStruct);
		
	}
	
}
