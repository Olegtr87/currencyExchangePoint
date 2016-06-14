package com.epam.vasilevsky.exchanger.webapp.page.balanceuser;

import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.balanceuser.panel.BalanceUserListPanel;

public class BalanceUserPage extends AbstractHomePage {
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new BalanceUserListPanel("list-panel"));
		
	}
}
