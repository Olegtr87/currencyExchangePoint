package com.epam.vasilevsky.exchanger.webapp.page.operations;

import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.operations.panel.OperationsListPanel;

public class OperationPage extends AbstractHomePage {

	@Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(new OperationsListPanel("list-panel"));
        
    }

}
