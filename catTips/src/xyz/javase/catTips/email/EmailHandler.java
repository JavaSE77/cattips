package xyz.javase.catTips.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import xyz.javase.catTips.generate.FileHandler;

public class EmailHandler {
	
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
	 * */
	public EmailHandler(String sender, String usrname, String passwd, String host, String hostname) {
		username = usrname;
		password = passwd;
		mailHost = host;
		mailHostname = hostname;
	}
	
	protected void sendEmail(String recipient, String catMsg) { 
		
	    Properties props = new Properties();
	    props.put(mailHost, mailHostname);
	    Session session = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(username);
	        msg.setRecipients(Message.RecipientType.TO,
	                          recipient);
	        msg.setSubject("Daily Cat Tip");
	        msg.setSentDate(new Date());
	        msg.setText(catMsg);
	        Transport.send(msg, username, password);
	        System.out.println("Email has been sent");
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }

		
	}
	
	public void sendCatTipViaEmail(String recipient) {
		String msg = FileHandler.appendStopMessage(FileHandler.generateCatTip());
		sendEmail(recipient, msg);
		
	}
	
	
	public void sendCatTipViaGateway(String recipient, String gateway) {
		sendEmail(recipient  +"@" + gateway, "cat tipz");
	}
	
	public boolean testEmail(String recipient) {
		
	    Properties props = new Properties();
	    props.put(mailHost, mailHostname);
	    Session session = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(username);
	        msg.setRecipients(Message.RecipientType.TO,
	                          recipient);
	        msg.setSubject("Daily Cat Tip");
	        msg.setSentDate(new Date());
	        msg.setText("Thank you for subscribing to cat tips daily cat facts! Please reply to this message to confirm your subscription.");
	        Transport.send(msg, username, password);
	        System.out.println("Email has been sent");
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	        return false;
	    }
		
		return true;
	}
}
