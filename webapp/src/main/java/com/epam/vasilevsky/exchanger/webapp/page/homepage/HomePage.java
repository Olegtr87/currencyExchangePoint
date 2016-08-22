package com.epam.vasilevsky.exchanger.webapp.page.homepage;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;

import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;

@AuthorizeInstantiation(value = { "Administrator", "Client" })
public class HomePage extends AbstractHomePage {

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}
}