package com.epam.vasilevsky.exchanger.webapp.page.home;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;
import com.epam.vasilevsky.exchanger.webapp.page.homepage.HomePage;

public class LoginPage extends AbstractPage {

    public LoginPage() {
        super();
        add(new Link("linkhome") {
            @Override
            public void onClick() {
                setResponsePage(new HomePage());
            }
        });
        
        add(new Label("message", "Ввод логина и пароля:"));
    }

}
