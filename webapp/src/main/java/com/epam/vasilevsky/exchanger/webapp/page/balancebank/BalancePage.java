package com.epam.vasilevsky.exchanger.webapp.page.balancebank;

import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.balancebank.panel.BalanceListPanel;
import com.epam.vasilevsky.exchanger.webapp.page.operations.panel.OperationsListPanel;

public class BalancePage extends AbstractHomePage {

	@Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(new BalanceListPanel("list-panel"));
        
    }

}
