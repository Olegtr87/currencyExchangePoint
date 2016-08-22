package com.epam.vasilevsky.exchanger.webapp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CodeCurrency;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CourseNBRBImpl;
import com.epam.vasilevsky.exchanger.webapp.component.localization.LanguageSelectionComponent;
import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanel;
import com.epam.vasilevsky.exchanger.webapp.page.course.CoursePage;
import com.epam.vasilevsky.exchanger.webapp.page.mail.MailModalWin;

public abstract class AbstractPage extends WebPage {

	public AbstractPage() {
		super();
	}

	public AbstractPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new LanguageSelectionComponent("language-select"));

		final MailModalWin win1 = new MailModalWin("modalMail");
		win1.setTitle(getString("email.title"));
		win1.setResizable(false);
		add(win1);

		AjaxLink link = new AjaxLink("link-mail") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				win1.show(target);
			}
		};
		add(link);

		CourseNBRBImpl course = new CourseNBRBImpl();
		if (course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.EUR)) != null) {
			add(new Label("course",
					String.format("%s %s:  EURO - %s   USD - %s   RUB - %s   PLZ - %s", getString("course.nbrb"),
							course.getDate(),
							course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.EUR)),
							course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.USD)),
							course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.RUB)),
							course.getCourse(CodeCurrency.getCurrencyFromName(CurrencyName.PLZ)))));
		} else
			add(new Label("course", ""));
	}
}
