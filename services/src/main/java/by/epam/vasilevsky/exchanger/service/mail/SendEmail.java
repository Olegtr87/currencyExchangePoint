package by.epam.vasilevsky.exchanger.service.mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	private String email;
	private String password;

	public SendEmail(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public static void main(String[] args){
		SendEmail sendEmail=new SendEmail("olegtr87@gmail.com","Bulochkin2003");
		sendEmail.send("Tema", "text", "olegtr87@gmail.com");
	}
	public void send(String subject, String text, String toEmail) {
		Properties props = new Properties();// properties for gmail
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		// Get the default Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		try {
			Message message = new MimeMessage(session); // Create a default MimeMessage object.
			message.setFrom(new InternetAddress(email)); // Set From: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); // Set To: header field of the header.
			message.setSubject(subject); // Set Subject: header field
			message.setText(text); // Now set the actual message

			Transport.send(message); // Send message
			System.out.println("Sent message successfully!");
		} catch (MessagingException e) {
			System.out.println("Message not sent!!!");
		}
	}
}
