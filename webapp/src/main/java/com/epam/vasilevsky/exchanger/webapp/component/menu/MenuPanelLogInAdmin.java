package com.epam.vasilevsky.exchanger.webapp.component.menu;

import java.util.Date;

import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.webapp.page.balancebank.BalancePage;
import com.epam.vasilevsky.exchanger.webapp.page.course.CoursePage;
import com.epam.vasilevsky.exchanger.webapp.page.operations.OperationPage;

public class MenuPanelLogInAdmin extends MenuPanel {

	public MenuPanelLogInAdmin(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Link("link-operation") {
			@Override
			public void onClick() {
				setResponsePage(new OperationPage());
			}
		});

		add(new Link("link-course") {
			@Override
			public void onClick() {
				setResponsePage(new CoursePage(new ExchangeRate()));
			}
		});
		
		add(new Link("link-balance") {
			@Override
			public void onClick() {
				setResponsePage(new BalancePage(new String()));
			}
		});
	}

}
