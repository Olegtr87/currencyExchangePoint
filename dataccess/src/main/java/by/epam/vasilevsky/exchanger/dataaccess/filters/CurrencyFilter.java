package by.epam.vasilevsky.exchanger.dataaccess.filters;

import by.epam.vasilevsky.exchanger.datamodel.CurrencyName;

public class CurrencyFilter extends AbstractFilter{
	
	private CurrencyName nameCurrency;

	public CurrencyName getNameCurrency() {
		return nameCurrency;
	}

	public void setNameCurrency(CurrencyName nameCurrency) {
		this.nameCurrency = nameCurrency;
	}

	
}
