package com.epam.vasilevsky.exchanger.service;

public interface SendEmail {
	void send(String subject, String text, String toEmail);
}
