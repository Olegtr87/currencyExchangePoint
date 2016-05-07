package com.epam.vasilevsky.exchanger.webapp.page.home;

import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;
import com.epam.vasilevsky.exchanger.webapp.page.product.ProductPage;

public class HomePage extends AbstractPage {

    public HomePage() {
        super();
        add(new Link("linkproduct") {
            @Override
            public void onClick() {
                setResponsePage(new ProductPage());
            }
        });
    }

}
