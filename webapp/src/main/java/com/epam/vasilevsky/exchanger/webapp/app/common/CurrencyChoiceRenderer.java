package com.epam.vasilevsky.exchanger.webapp.app.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import com.epam.vasilevsky.exchanger.datamodel.Currency;

public class CurrencyChoiceRenderer extends ChoiceRenderer<Currency> {

	public static final CurrencyChoiceRenderer INSTANCE = new CurrencyChoiceRenderer();

	private CurrencyChoiceRenderer() {
		super();
	}

	@Override
	public Object getDisplayValue(Currency object) {
		return object.getName();
	}

	@Override
	public String getIdValue(Currency object, int index) {
		return String.valueOf(object.getId());
	}

}
