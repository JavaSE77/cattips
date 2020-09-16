package xyz.javase.catTips;

import java.util.List;

import xyz.javase.catTips.email.EmailHandler;
import xyz.javase.catTips.generate.FileHandler;

public class Main {

	public static void main(String args[]) {
		
		if(args.length < 1) {
			System.out.println("Please include the path to the setup file");
		}
		
		String setupFileLocation = args[0];
				//"C:\Users\Russell\Desktop\setup.txt";
		
		System.out.println("Starting cat tips");
		
		List<String> setup = Setup.setupMethod(setupFileLocation);
		
		EmailHandler emailHandler = new EmailHandler(setup.get(0),setup.get(1),setup.get(2),setup.get(3),setup.get(4));
		

	}
	
}
