package com.epam.vasilevsky.exchanger.service;

public interface EmailService {
	void send(String subject, String text, String toEmail);
}
