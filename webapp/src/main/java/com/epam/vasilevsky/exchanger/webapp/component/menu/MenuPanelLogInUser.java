package com.epam.vasilevsky.exchanger.webapp.component.menu;

import javax.inject.Inject;

import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.dataaccess.UserProfileDao;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.page.converter.ConverterPage;
import com.epam.vasilevsky.exchanger.webapp.page.register.RegisterPage;
import com.epam.vasilevsky.exchanger.webapp.page.transaction.TransactionsPage;
import com.epam.vasilevsky.exchanger.webapp.page.transaction.panel.TransactionsListPanel;

public class MenuPanelLogInUser extends MenuPanel{
	
	@Inject
	UserProfileDao userProfileDao;

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
        
        add(new Link("link-converter") {
            @Override
            public void onClick() {
                setResponsePage(new ConverterPage(new ExchangeRate()));
            }
        });
        
        add(new Link("link-cabinet") {
            @Override
            public void onClick() {
            	UserCredentials userCredentials = AuthorizedSession.get().getLoggedUser();
            	UserProfile userProfile=userProfileDao.get(AuthorizedSession.get().getLoggedUser().getId());
                setResponsePage(new RegisterPage(userCredentials,userProfile));
            }
        });
    }

}
