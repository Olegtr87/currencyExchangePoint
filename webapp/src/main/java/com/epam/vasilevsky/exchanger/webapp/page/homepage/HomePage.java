package com.epam.vasilevsky.exchanger.webapp.page.homepage;

import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanel;
import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanelLogInAdmin;
import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanelLogInUser;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractPage;

public class HomePage extends AbstractPage {
	 @Override
	    protected void onInitialize() {
	        super.onInitialize();
	        
	        //add(new MenuPanelLogInUser("menu-panel"));
	        add(new MenuPanelLogInAdmin("menu-panel"));
	    }
}
