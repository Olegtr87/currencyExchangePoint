package com.epam.vasilevsky.exchanger.service;

import java.util.List;
import javax.transaction.Transactional;

import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;

import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;

public interface ExchangeRateService {
	
	@Transactional
	void add(ExchangeRate exchangeRate, Currency currencyIdFrom, Currency currencyIdTo);
	
	ExchangeRate get(Long id);
	
	@Transactional
    void update(ExchangeRate exchangeRate);

    @Transactional
    void delete(Long id);
    
    List<ExchangeRate> find(ExchangeRateFilter filter);
    
    Long count(ExchangeRateFilter filter);

    @Transactional
	void saveOrUpdate(ExchangeRate exchangeRate);
}
