package xyz.javase.catTips.subcribers;

import xyz.javase.catTips.email.EmailHandler;

public class ProviderLookup {

	public static void lookUpProvider(String user) {
		
		EmailHandler emailHandler = new EmailHandler("Cat Tips", "cattips@catlover.com", "JavaMail234!@#$%", "mail.smtp.host", "smtp.mail.com");
		
		String gateways[]= {"sms.alltelwireless.com","txt.att.net","sms.myboostmobile.com","mms.cricketwireless.net","txt.att.net","msg.fi.google.com",
				"mymetropcs.com","text.republicwireless.com","messaging.sprintpcs.com","tmomail.net","email.uscc.net","vtext.com","vmobl.com"};
		boolean found = false;
		int i = 0;
		while((found == false) && (i <= gateways.length)) {
			
			if(emailHandler.testEmail(user + "@" + gateways[i])) {
				//found = true;
				System.out.println("Found " + user + " at " + gateways[i]);
			} else {
				System.out.println("\nNot " + gateways[i]);
			}
			
			i++;
		}
		
		
		
		
	}
}
