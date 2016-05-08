package com.epam.vasilevsky.exchanger.webapp.page.homepage;

import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.webapp.app.check.CheckPage;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;

public class HomePage extends AbstractPage {
	public HomePage() {
        super();
        add(new Link("linkcheck") {
            @Override
            public void onClick() {
                setResponsePage(new CheckPage());
            }
        });
    }
}
