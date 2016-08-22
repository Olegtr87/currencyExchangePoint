package com.epam.vasilevsky.exchanger.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.vasilevsky.exchanger.service.mail.SendEmailImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class MailTest {
	
	@Test
	public void test(){
		SendEmailImpl sendEmail=new SendEmailImpl("olegtr87@gmail.com","Bulochkin2003");
		try {
			sendEmail.send("Tema", "text", "olegtr87@gmail.com");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
