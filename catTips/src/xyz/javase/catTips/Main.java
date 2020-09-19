package xyz.javase.catTips;

import java.util.ArrayList;
import java.util.List;

import xyz.javase.catTips.email.EmailHandler;
import xyz.javase.catTips.generate.FileHandler;
import xyz.javase.catTips.subcribers.Subscriber;

public class Main {

	public static void main(String args[]) {
		
		if(args.length < 1) {
			System.out.println("Please include the path to the setup file");
		}
		
		String setupFileLocation = args[0];
				//"C:\Users\Russell\Desktop\setup.txt";
		
		System.out.println("Starting cat tips");
		
		while(true) {
		
		List<String> setup = Setup.setupMethod(setupFileLocation);
		
		Long interval = Long.parseLong(setup.get(5));
		
		EmailHandler emailHandler = new EmailHandler(setup.get(0),setup.get(1),setup.get(2),setup.get(3),setup.get(4));
		
		List<Subscriber> subs = new ArrayList<>();
		
		String catTip = FileHandler.generateCatTip();
		
		List<String> subFlNames = FileHandler.getSubscriberFileNames();
		for(String subFileName : subFlNames) {
			subs.add(new Subscriber(subFileName));
		}
		
		for(Subscriber cws : subs) {
			//Check if the user is active
			if(cws.checkActiveStatus()) {
				//Check if the user has received a message before
				if(cws.getLastSent() <= 0) {
					emailHandler.confirmSubscription(cws.getAddress());
					cws.setLastSent(System.currentTimeMillis());
				}
				
				if(cws.getLastSent() < (System.currentTimeMillis() - interval)) {
					emailHandler.sendEmail(cws.getAddress(), "Daily Cat Tip", catTip);
					cws.setLastSent(System.currentTimeMillis());
				}
			} else {
				//If the user is not active, remove them from the list
				subs.remove(cws);
			}
		}

		
		setup.clear();
		subs.clear();
		subFlNames.clear();
		try {
			Thread.sleep(interval + 30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
