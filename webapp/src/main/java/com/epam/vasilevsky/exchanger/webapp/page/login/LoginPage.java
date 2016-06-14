package com.epam.vasilevsky.exchanger.webapp.page.login;

import javax.inject.Inject;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.service.UserCredentialsService;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CodeCurrency;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CourseNBRBImpl;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;
import com.epam.vasilevsky.exchanger.webapp.page.password.ModalWin;
import com.epam.vasilevsky.exchanger.webapp.page.register.RegisterPage;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;

public class LoginPage extends AbstractPage {

	private String login;
	private String password;

	@Inject
	UserCredentialsService userCredentialsService;

	ModalWindow modalWindow;

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

		Options options = new Options();
		options.set("button", true);
		final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback", options);
		this.add(feedback);

		form1.add(new SubmitLink("reg-btn") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new RegisterPage(new UserCredentials(), new UserProfile(), new BankAccountUser()));
			}
		});

		final ModalWin win = new ModalWin("modalPassword");
		win.setTitle(getString("password.page.title"));
		win.setResizable(false);
		add(win);

		AjaxLink link = new AjaxLink("link") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				win.show(target);
			}
		};
		add(link);

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
					error(getString("login.err.auth"));
				}
			}
		});
		add(form);
		add(form1);
	}
}
