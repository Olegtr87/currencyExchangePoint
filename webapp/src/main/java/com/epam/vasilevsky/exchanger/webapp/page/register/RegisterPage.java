package com.epam.vasilevsky.exchanger.webapp.page.register;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.datamodel.UserRole;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;
import com.epam.vasilevsky.exchanger.webapp.page.homepage.HomePage;
import com.epam.vasilevsky.exchanger.webapp.page.login.LoginPage;


public class RegisterPage extends AbstractPage {

	@Inject
	private UserService userService;
	
	UserCredentials userCredentials;
	UserProfile userProfile;
	
	public RegisterPage (PageParameters parameters) {
		super(parameters);
	}
	
	public RegisterPage() {
        super();
    }
	
	public RegisterPage(UserCredentials userCredentials, UserProfile userProfile) {
		super();
		this.userCredentials = userCredentials;
		this.userProfile = userProfile;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form form = new Form("form", new CompoundPropertyModel<UserProfile>(userProfile));
		add(form);
		
//		TextField<String> loginField = new TextField<>("login");
//		loginField.setRequired(true);
//		form.add(loginField);
//		
//		TextField<String> passwordField = new TextField<>("password");
//		passwordField.setRequired(true);
//		form.add(passwordField);
		
		userCredentials.setRole(UserRole.Client);
		userCredentials.setLogin("login2");
		userCredentials.setPassword("password2");
		
		TextField<String> firstNameField = new TextField<>("firstName");
		firstNameField.setRequired(true);
		form.add(firstNameField);
		
		TextField<String> lastNameField = new TextField<>("lastName");
		lastNameField.setRequired(true);
		form.add(lastNameField);
		
		TextField<String> patronymicField = new TextField<>("patronymic");
		patronymicField.setRequired(true);
		form.add(patronymicField);
		
		TextField<String> numberPassportField = new TextField<>("numberPassport");
		numberPassportField.setRequired(true);
		form.add(numberPassportField);
		
		DateTextField dateIssueField = new DateTextField("dateIssue");
		dateIssueField.add(new DatePicker());
		dateIssueField.setRequired(true);
        form.add(dateIssueField);
		
		TextField<String> issuedField = new TextField<>("issued");
		issuedField.setRequired(true);
		form.add(issuedField);


		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				userService.register(userProfile,userCredentials);
				setResponsePage(new LoginPage());
			}
		});

		add(new FeedbackPanel("feedback"));

	}


}
