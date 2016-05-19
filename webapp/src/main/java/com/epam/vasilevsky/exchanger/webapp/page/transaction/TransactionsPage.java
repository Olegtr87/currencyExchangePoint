package com.epam.vasilevsky.exchanger.webapp.page.transaction;

import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.transaction.panel.TransactionsListPanel;

public class TransactionsPage extends AbstractHomePage {

	public TransactionsPage() {
        super();
        add(new TransactionsListPanel("list-panel"));
    }

}
