package com.epam.vasilevsky.exchanger.webapp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanel;

public abstract class AbstractPage extends WebPage {

	public AbstractPage() {
        super();
    }
	
	public AbstractPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
    protected void onInitialize() {
        super.onInitialize();
    }
}
