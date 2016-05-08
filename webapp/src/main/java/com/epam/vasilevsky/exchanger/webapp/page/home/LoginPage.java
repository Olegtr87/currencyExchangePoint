package com.epam.vasilevsky.exchanger.webapp.page.home;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CodeCurrency;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CourseNBRB;
import com.epam.vasilevsky.exchanger.webapp.app.registerpage.RegisterPage;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;
import com.epam.vasilevsky.exchanger.webapp.page.homepage.HomePage;

public class LoginPage extends AbstractPage {

	public LoginPage() {
		super();

		add(new Link("linkhome") {
			@Override
			public void onClick() {
				setResponsePage(new HomePage());
			}
		});

		add(new Link("registration") {
			@Override
			public void onClick() {
				setResponsePage(new RegisterPage());
			}
		});

		add(new Label("message", "Ввод логина и пароля:"));
		add(new TextField("name"));
		add(new TextField("email"));

		CourseNBRB course = new CourseNBRB();
		add(new Label("course",
				String.format("Курсы валют НБРБ на %s:  EURO - %s   USD - %s   RUB - %s   PLZ - %s", course.getDate(),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.EUR)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.USD)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.RUB)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.PLZ)))));
	}

}
