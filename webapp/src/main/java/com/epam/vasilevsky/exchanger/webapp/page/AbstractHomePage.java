package com.epam.vasilevsky.exchanger.webapp.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanelLogInAdmin;
import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanelLogInUser;

public abstract class AbstractHomePage extends AbstractPage {

	public AbstractHomePage(PageParameters parameters) {
		super(parameters);
	}

	public AbstractHomePage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (AuthorizedSession.get().getRoles().contains("Administrator")) {
			add(new MenuPanelLogInAdmin("menu-panel"));
		} else {
			add(new MenuPanelLogInUser("menu-panel"));
		}

	}
}
