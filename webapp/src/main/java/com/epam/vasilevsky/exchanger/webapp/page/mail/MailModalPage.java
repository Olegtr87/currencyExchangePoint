
package com.epam.vasilevsky.exchanger.webapp.page.mail;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.epam.vasilevsky.exchanger.dataaccess.filters.UserCredentialsFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.UserFilter;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.service.mail.SendEmailImpl;
import com.epam.vasilevsky.exchanger.webapp.app.others.Passwords;
import com.epam.vasilevsky.exchanger.webapp.page.login.LoginPage;
import com.epam.vasilevsky.exchanger.webapp.page.password.ModalWin;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.form.button.IndicatingAjaxButton;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButtons;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogIcon;
import com.googlecode.wicket.jquery.ui.widget.dialog.MessageDialog;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;
import com.googlecode.wicket.kendo.ui.widget.editor.Editor;

public class MailModalPage extends WebPage {

	SendEmailImpl sendEmailImpl;

	private MailModalWin mailModalWin;

	public MailModalPage() {
		super();
	}

	public MailModalPage(PageParameters parameters) {
		super(parameters);
	}

	public MailModalPage(final MailModalWin window) {
		this.mailModalWin = window;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Options options = new Options();
		options.set("button", true);

		final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback", options);
		this.add(feedback);

		sendEmailImpl = new SendEmailImpl(Passwords.EMAIL, Passwords.PASSWORD);

		final Form<Void> form = new Form<Void>("form");
		add(form);
		
		RequiredTextField<String> name = new RequiredTextField<>("name",new Model<String>());
		form.add(name.setRequired(true));
		
		RequiredTextField<String> telephone = new RequiredTextField<>("telephone",new Model<String>());
		telephone.setRequired(true);
		form.add(telephone.setRequired(true));
		
		RequiredTextField<String> subject = new RequiredTextField<>("subject",new Model<String>());
		subject.setRequired(true);
		form.add(subject.setRequired(true));
		
		TextArea<String> textArea = new TextArea<>("text",new Model<String>());
		textArea.setRequired(true);
		
		form.add(textArea.setRequired(true));

		IndicatingAjaxButton link = new IndicatingAjaxButton("mailer") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				sendEmailImpl.send(subject.getModelObject(), getText(), Passwords.EMAIL);
				this.info(getString("email.info"));
				target.add(feedback);
			}
			
			private String getText(){
				return name.getModelObject()+" "+telephone.getModelObject()+"\n"+textArea.getModelObject();
			}
		};
		
		form.add(link);
		add(form);
	}
}
