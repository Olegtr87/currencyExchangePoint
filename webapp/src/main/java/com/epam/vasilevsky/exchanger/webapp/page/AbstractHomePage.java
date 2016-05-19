package com.epam.vasilevsky.exchanger.webapp.page;

import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanelLogInAdmin;
import com.epam.vasilevsky.exchanger.webapp.component.menu.MenuPanelLogInUser;

public abstract class AbstractHomePage extends AbstractPage {

	@Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(new MenuPanelLogInAdmin("menu-panel"));
        //add(new MenuPanelLogInUser("menu-panel"));
        
    }
}
