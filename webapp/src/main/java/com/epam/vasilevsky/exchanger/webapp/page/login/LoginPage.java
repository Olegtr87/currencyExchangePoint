package com.epam.vasilevsky.exchanger.webapp.page.login;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.service.UserCredentialsService;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CodeCurrency;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CourseNBRBImpl;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;
import com.epam.vasilevsky.exchanger.webapp.page.homepage.HomePage;
import com.epam.vasilevsky.exchanger.webapp.page.register.RegisterPage;

public class LoginPage extends AbstractPage {

	@Inject
	UserCredentialsService userCredentialsService;
	public LoginPage() {
		super();
		System.out.println(userCredentialsService);

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new Link("linkhome") {
			@Override
			public void onClick() {
				setResponsePage(new HomePage());
			}
		});

		add(new Link("registration") {
			@Override
			public void onClick() {
				setResponsePage(new RegisterPage(new UserCredentials(),new UserProfile()));
			}
		});

		add(new Label("message", "Ввод логина и пароля:"));
		add(new TextField("name"));
		add(new TextField("email"));

		CourseNBRBImpl course = new CourseNBRBImpl();
		add(new Label("course",
				String.format("Курсы валют НБРБ на %s:  EURO - %s   USD - %s   RUB - %s   PLZ - %s", course.getDate(),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.EUR)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.USD)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.RUB)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.PLZ)))));
	}
}
