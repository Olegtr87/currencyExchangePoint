package com.epam.vasilevsky.exchanger.webapp.page.register;

import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
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
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.*;

import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.datamodel.UserRole;
import com.epam.vasilevsky.exchanger.service.BankUserAccountService;
import com.epam.vasilevsky.exchanger.service.UserCredentialsService;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;
import com.epam.vasilevsky.exchanger.webapp.page.homepage.HomePage;
import com.epam.vasilevsky.exchanger.webapp.page.login.LoginPage;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButtons;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogIcon;
import com.googlecode.wicket.jquery.ui.widget.dialog.MessageDialog;

public class RegisterPage extends AbstractPage {

	@Inject
	private UserService userService;

	@Inject
	private UserCredentialsService userCredentialsService;

	UserCredentials userCredentials;
	UserProfile userProfile;
	BankAccountUser bankAccountUser;
	
	@Inject
	private BankUserAccountService bankUserAccountService;

	public RegisterPage(PageParameters parameters) {
		super(parameters);
	}

	public RegisterPage() {
		super();
	}

	public RegisterPage(UserCredentials userCredentials, UserProfile userProfile, BankAccountUser bankAccountUser) {
		super();
		this.userCredentials = userCredentials;
		this.userProfile = userProfile;
		this.bankAccountUser=bankAccountUser;
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

		TextField<String> loginField = new TextField<>("login", new PropertyModel(userCredentials, "login"));
		loginField.setRequired(true);
		loginField.add(EmailAddressValidator.getInstance());
		form.add(loginField);

		TextField<String> passwordField = new TextField<>("password", new PropertyModel(userCredentials, "password"));
		passwordField.setRequired(true);
		form.add(passwordField);

		userCredentials.setRole(UserRole.Client);

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
		dateIssueField.add(DateValidator.maximum(new Date()));
		form.add(dateIssueField);

		DateTextField createdField = new DateTextField("created");
		createdField.setEnabled(false);
		form.add(createdField);

		TextField<String> issuedField = new TextField<>("issued");
		issuedField.setRequired(true);
		form.add(issuedField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();

				if (AuthenticatedWebSession.get().isSignedIn()) {
					userService.updateProfile(userProfile);
					userService.updateCredentials(userCredentials);
					setResponsePage(new LoginPage());
				} 
				else if (userCredentialsService.findByLogin(loginField.getModelObject()).getLogin()
						.equals(loginField.getModelObject())) {
					error(getString("register.error.login"));
				} 
				else {
					userService.register(userProfile, userCredentials);
					regUserAccaunt();
					setResponsePage(new LoginPage());
				}
			}
		});

		SubmitLink link = new SubmitLink("exit") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new LoginPage());
			}
		};
		link.setDefaultFormProcessing(false);
		form.add(link);

		add(new FeedbackPanel("feedback"));
	}
	
	private void regUserAccaunt(){
		bankAccountUser=new BankAccountUser();
		CurrencyName[] currency=CurrencyName.values();
		for(int i=0;i<CurrencyName.values().length;i++){
			bankAccountUser.setBalance(0);
			bankAccountUser.setCurrency(currency[i]);
			bankAccountUser.setUser(userCredentials);
			bankUserAccountService.add(bankAccountUser);
			bankAccountUser=new BankAccountUser();
		}
		
		
	}
}
