package com.alfonso.kioscoApp.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvioMail {
	private final Properties properties = new Properties();
 
	private Session session;
 
	private void init() {
 
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",587);
		properties.put("mail.smtp.mail.sender","dirielfran@gmail.com");
		properties.put("mail.smtp.user", "dirielfran@gmail.com");
		properties.put("mail.smtp.auth", "true");
 
		session = Session.getDefaultInstance(properties);
	}
 
	public void sendEmail(String address, String subject, String body){
 
		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
			message.setSubject(subject);
			message.setText(body);
			message.setContent(body, "text/html");
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), "Nala120-");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}catch (MessagingException me){
			System.out.println(me.getMessage());
			return;
		}
		
	}
 
}