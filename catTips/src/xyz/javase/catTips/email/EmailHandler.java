package xyz.javase.catTips.email;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import xyz.javase.catTips.generate.FileHandler;

public class EmailHandler {
	
	private String sender;
	private String username;
	private String password;
	private String mailHost;
	private String mailHostname;

	
	/**
	 * String sender -> What you want the user to see the sender as
	 * String usrname -> the email you are sending from
	 * String passwd -> the password for above account
	 * String host -> the mail server of the above account
	 * String hostname -> the hostname of the above account (arbitrary)
	 * 
	 * @Constructor
	 * */
	public EmailHandler(String sendr, String usrname, String passwd, String host, String hostname) {
		sender = sendr;
		username = usrname;
		password = passwd;
		mailHost = host;
		mailHostname = hostname;
	}
	
	/**
	 * Sends an email.
	 * Recipient is the full address of the email recipient
	 * String subject will be the subject of the message
	 * String message will be the message sent to the user
	 * */
	public void sendEmail(String recipient, String subject, String message) { 
		
	    Properties props = new Properties();
	    props.put(mailHost, mailHostname);
	    Session session = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(username);
	        msg.setRecipients(Message.RecipientType.TO,
	                          recipient);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setText(message);
	        Transport.send(msg, username, password);
	        System.out.println("Email has been sent");
	    } catch (MessagingException mex) {
	    	System.out.println(recipient);
	        System.out.println(" send failed, exception: " + mex);
	    }

		
	}
	

	/**
	 * This will confirm a users subscription to the service by sending them an email. If the email goes through, then we will assume they
	 * are subscribed. If the email does not go through, then assume the address is invalid, or the user is not accepting messages from us
	 * with sms gateways, this will also occur if the user has blocked the messages, or has not responded to messages in a while. 
	 * @return a boolean value
	 * */
	public boolean confirmSubscription(String recipient) {
		
		List<String> msgArray = FileHandler.getFirstMsgArray();
		if(msgArray.size() != 2) { 
			System.err.println("First message setup file is empty. Unable to add new subscribers. ");
			return false;
		}
		
	    Properties props = new Properties();
	    props.put(mailHost, mailHostname);
	    Session session = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(username);
	        msg.setRecipients(Message.RecipientType.TO,
	                          recipient);
	        msg.setSubject(msgArray.get(0));
	        msg.setSentDate(new Date());
	        msg.setText(msgArray.get(1));
	        Transport.send(msg, username, password);
	        System.out.println("Email has been sent");
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	        return false;
	    }
		
		return true;
	}
}
