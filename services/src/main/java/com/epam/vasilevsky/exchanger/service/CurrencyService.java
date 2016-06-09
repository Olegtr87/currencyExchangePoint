package com.epam.vasilevsky.exchanger.service;

import java.util.List;
import javax.transaction.Transactional;

import com.epam.vasilevsky.exchanger.dataaccess.filters.CurrencyFilter;

import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.Currency;

public interface CurrencyService {
	@Transactional
    void add(Currency currency, Balance balance);

	Currency getCurrency(Long id);

    Balance getBalance(Long id);
    
    List<Currency> getAll();

    @Transactional
    void updateCurrency(Currency currency);
    
    @Transactional
    void updateBalance(Balance balance);

    @Transactional
    void delete(Long id);
    
    List<Currency> find(CurrencyFilter filter);

	long count(CurrencyFilter currencyFilter);
}
