package com.epam.vasilevsky.exchanger.dataaccess.filters;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;

public class CurrencyFilter extends AbstractFilter{
	
	private CurrencyName nameCurrency;

	public CurrencyName getNameCurrency() {
		return nameCurrency;
	}

	public void setNameCurrency(CurrencyName nameCurrency) {
		this.nameCurrency = nameCurrency;
	}

	
}
