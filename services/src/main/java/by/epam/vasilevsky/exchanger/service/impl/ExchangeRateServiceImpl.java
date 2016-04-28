package by.epam.vasilevsky.exchanger.service.impl;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import by.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import by.epam.vasilevsky.exchanger.dataaccess.ExchangeRateDao;
import by.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.service.ExchangeRateService;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
	private static Logger LOGGER = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);
	
	@Inject
	ExchangeRateDao exchangeRateDao;
	
	@Inject
	CurrencyDao currencyDao;

	@Override
	public void add(ExchangeRate exchangeRate, Currency currencyIdFrom, Currency currencyIdTo) {
		exchangeRate.setCurrencyIdFrom(currencyIdFrom);
		exchangeRate.setCurrencyIdTo(currencyIdTo);
		exchangeRateDao.insert(exchangeRate);
		LOGGER.info("ExchangeRate {} added", exchangeRate);
	}

	@Override
	public ExchangeRate get(Long id) {
		return exchangeRateDao.get(id);
	}

	@Override
	public void update(ExchangeRate exchangeRate) {
		exchangeRateDao.update(exchangeRate);
		LOGGER.info("ExchangeRate {} updated", exchangeRate);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("ExchangeRate {} deleted", exchangeRateDao.get(id));
		exchangeRateDao.delete(id);
	}

	@Override
	public List<ExchangeRate> find(ExchangeRateFilter filter) {
		LOGGER.info("Search for ExchangeRate perfomed!");
		return exchangeRateDao.find(filter);
	}	

}
