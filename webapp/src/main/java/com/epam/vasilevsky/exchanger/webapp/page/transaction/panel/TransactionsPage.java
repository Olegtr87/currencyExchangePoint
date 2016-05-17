package com.epam.vasilevsky.exchanger.webapp.page.transaction.panel;

import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.transaction.TransactionsListPanel;

public class TransactionsPage extends AbstractHomePage {

	public TransactionsPage() {
        super();
        add(new TransactionsListPanel("list-panel"));
    }

}
