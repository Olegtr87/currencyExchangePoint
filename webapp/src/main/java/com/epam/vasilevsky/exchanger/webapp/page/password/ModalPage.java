
package com.epam.vasilevsky.exchanger.webapp.page.password;

import java.util.List;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.dataaccess.filters.UserCredentialsFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.UserFilter;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.service.mail.SendEmailImpl;
import com.epam.vasilevsky.exchanger.webapp.app.others.Passwords;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButtons;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogIcon;
import com.googlecode.wicket.jquery.ui.widget.dialog.MessageDialog;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;

public class ModalPage extends WebPage {

	@Inject
	UserService userService;

	SendEmailImpl sendEmailImpl;

	private UserCredentials userCredentials;

	private ModalWin modalWin;

	public ModalPage() {
		super();
	}

	public ModalPage(PageParameters parameters) {
		super(parameters);
	}

	public ModalPage(final ModalWin window) {
		this.modalWin = window;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		userCredentials = new UserCredentials();
		sendEmailImpl = new SendEmailImpl(Passwords.EMAIL, Passwords.PASSWORD);

		Form form = new Form("form", new CompoundPropertyModel<UserCredentials>(userCredentials));
		add(form);
		
		Options options = new Options();
		options.set("button", true);
		final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback", options);
		add(feedback);
		
		RequiredTextField<String> loginField = new RequiredTextField<>("login");
		loginField.setRequired(true);		
		form.add(loginField);
		
		AjaxSubmitLink link = new AjaxSubmitLink("link") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				UserFilter filter = new UserFilter();
				filter.setLogin(userCredentials.getLogin());
				List<UserProfile> list = userService.find(filter);

				if (list.size() > 0) {
					Long id = list.get(0).getId();
					
					try {
						sendEmailImpl.send(getString("email.subject"),
								getString("email.text") + userService.getCredentials(id).getPassword(),
								userCredentials.getLogin());
					} catch (AddressException e) {
						e.printStackTrace();
					} catch (MessagingException e) {
						this.warn(getString("email.error.connect"));
					}

					modalWin.close(target);
				} else {
					this.warn(getString("email.error"));
					target.add(feedback);
				}
			}
		};

		form.add(link);
		add(form);
	}
}
