package com.epam.vasilevsky.exchanger.webapp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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

		add(new Link("link-info") {
			@Override
			public void onClick() {
			}
		});
	}
}
