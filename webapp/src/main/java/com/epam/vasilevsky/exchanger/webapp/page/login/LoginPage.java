package com.epam.vasilevsky.exchanger.webapp.page.login;

import javax.inject.Inject;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.service.UserCredentialsService;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CodeCurrency;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CourseNBRBImpl;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;
import com.epam.vasilevsky.exchanger.webapp.page.register.RegisterPage;

public class LoginPage extends AbstractPage {

	private String login;
	private String password;

	@Inject
	UserCredentialsService userCredentialsService;

	public LoginPage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		// if already logged then should not see login page at all
		if (AuthenticatedWebSession.get().isSignedIn()) {
			setResponsePage(Application.get().getHomePage());
		}

		final Form<Void> form = new Form<Void>("form");
		final Form<Void> form1 = new Form<Void>("form1");
		form.setDefaultModel(new CompoundPropertyModel<LoginPage>(this));
		form.add(new RequiredTextField<String>("login"));
		form.add(new PasswordTextField("password"));

		form1.add(new SubmitLink("reg-btn") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new RegisterPage(new UserCredentials(), new UserProfile()));
			}
		});

		form.add(new SubmitLink("login-btn") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				if (Strings.isEmpty(login) || Strings.isEmpty(password)) {
					return;
				}
				final boolean authResult = AuthenticatedWebSession.get().signIn(login, password);
				if (authResult) {
					// continueToOriginalDestination();
					setResponsePage(Application.get().getHomePage());
				} else {
					error("authorization error");
				}
			}
		});

		add(new Label("message", "Ввод логина и пароля:"));

		add(form);
		add(form1);

		add(new FeedbackPanel("feedbackpanel"));

		CourseNBRBImpl course = new CourseNBRBImpl();
		add(new Label("course",
				String.format("Курсы валют НБРБ на %s:  EURO - %s   USD - %s   RUB - %s   PLZ - %s", course.getDate(),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.EUR)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.USD)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.RUB)),
						course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.PLZ)))));
	}
}
