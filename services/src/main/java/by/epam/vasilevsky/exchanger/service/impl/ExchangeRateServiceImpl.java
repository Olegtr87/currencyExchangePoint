package by.epam.vasilevsky.exchanger.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.epam.vasilevsky.exchanger.dataaccess.impl.CurrencyDaoImpl;
import by.epam.vasilevsky.exchanger.dataaccess.impl.ExchangeRateDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.service.ExchangeRateService;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
	
	@Inject
	ExchangeRateDaoImpl exchangeRateDaoImpl;
	
	@Inject
	CurrencyDaoImpl currencyDaoImpl;

	@Override
	public void add(ExchangeRate exchangeRate, Currency currencyIdFrom, Currency currencyIdTo) {
		exchangeRate.setCurrencyIdFrom(currencyIdFrom);
		exchangeRate.setCurrencyIdTo(currencyIdTo);
		exchangeRateDaoImpl.insert(exchangeRate);
	}

	@Override
	public ExchangeRate get(Long id) {
		return exchangeRateDaoImpl.get(id);
	}

	@Override
	public void update(ExchangeRate exchangeRate) {
		exchangeRateDaoImpl.update(exchangeRate);
	}

	@Override
	public void delete(Long id) {
		exchangeRateDaoImpl.delete(id);
	}	

}
