package com.epam.vasilevsky.exchanger.webapp.page.operations;

import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.operations.panel.OperationsListPanel;

public class OperationPage extends AbstractHomePage {

	@Override
    protected void onInitialize() {
        super.onInitialize();
        add(new OperationsListPanel("list-panel"));
    }

}
