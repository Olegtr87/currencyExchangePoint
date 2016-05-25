package com.epam.vasilevsky.exchanger.webapp.app.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;

public class CurrencyChoiceRenderer extends ChoiceRenderer<CurrencyName>{
	
	public static final CurrencyChoiceRenderer INSTANCE = new CurrencyChoiceRenderer();

    private CurrencyChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(CurrencyName object) {
        return object.name();
    }

    @Override
    public String getIdValue(CurrencyName object, int index) {
        return String.valueOf(object.ordinal());
    }
	
}
