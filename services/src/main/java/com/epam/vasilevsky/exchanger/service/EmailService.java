package com.epam.vasilevsky.exchanger.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
	void send(String subject, String text, String toEmail) throws AddressException, MessagingException;
}
