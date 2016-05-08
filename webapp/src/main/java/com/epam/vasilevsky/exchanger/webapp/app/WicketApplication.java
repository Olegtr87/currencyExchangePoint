package com.epam.vasilevsky.exchanger.webapp.app;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.stereotype.Component;

import com.epam.vasilevsky.exchanger.webapp.page.home.LoginPage;

@Component("wicketWebApplicationBean")
public class WicketApplication extends WebApplication {

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return LoginPage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        getMarkupSettings().setStripWicketTags(true);
        // add your configuration here
    }

}