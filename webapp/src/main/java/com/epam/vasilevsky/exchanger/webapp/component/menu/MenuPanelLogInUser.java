package com.epam.vasilevsky.exchanger.webapp.component.menu;

import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.webapp.page.homepage.HomePage;
import com.epam.vasilevsky.exchanger.webapp.page.transaction.TransactionsPage;

public class MenuPanelLogInUser extends MenuPanel{

	public MenuPanelLogInUser(String id) {
		super(id);
	}
	
	@Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(new Link("link-transactions") {
            @Override
            public void onClick() {
                setResponsePage(new TransactionsPage());
            }
        });
    }

}
