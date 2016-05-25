package com.epam.vasilevsky.exchanger.webapp.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import com.epam.vasilevsky.exchanger.webapp.page.login.LoginPage;

public class MenuPanel extends Panel {

	public MenuPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Link("link-logout") {
			@Override
			public void onClick() {
				getSession().invalidate();
                setResponsePage(LoginPage.class);
			}
		});
	}
}
