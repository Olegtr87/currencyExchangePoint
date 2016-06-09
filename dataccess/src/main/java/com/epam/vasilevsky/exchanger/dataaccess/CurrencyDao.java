package com.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.CurrencyFilter;

import com.epam.vasilevsky.exchanger.datamodel.Currency;

public interface CurrencyDao extends AbstractDao<Currency, Long>{
	
	List<Currency> find(CurrencyFilter filter);

	long count(CurrencyFilter currencyFilter);
}
