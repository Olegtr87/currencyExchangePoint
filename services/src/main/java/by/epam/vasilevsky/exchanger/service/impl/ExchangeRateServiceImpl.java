package by.epam.vasilevsky.exchanger.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import by.epam.vasilevsky.exchanger.dataaccess.impl.CurrencyDaoImpl;
import by.epam.vasilevsky.exchanger.dataaccess.impl.ExchangeRateDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.service.ExchangeRateService;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
	private static Logger LOGGER = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);
	
	@Inject
	ExchangeRateDaoImpl exchangeRateDaoImpl;
	
	@Inject
	CurrencyDaoImpl currencyDaoImpl;

	@Override
	public void add(ExchangeRate exchangeRate, Currency currencyIdFrom, Currency currencyIdTo) {
		exchangeRate.setCurrencyIdFrom(currencyIdFrom);
		exchangeRate.setCurrencyIdTo(currencyIdTo);
		exchangeRateDaoImpl.insert(exchangeRate);
		LOGGER.info("ExchangeRate {} added", exchangeRate);
	}

	@Override
	public ExchangeRate get(Long id) {
		return exchangeRateDaoImpl.get(id);
	}

	@Override
	public void update(ExchangeRate exchangeRate) {
		exchangeRateDaoImpl.update(exchangeRate);
		LOGGER.info("ExchangeRate {} updated", exchangeRate);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("ExchangeRate {} deleted", exchangeRateDaoImpl.get(id));
		exchangeRateDaoImpl.delete(id);
	}	

}
