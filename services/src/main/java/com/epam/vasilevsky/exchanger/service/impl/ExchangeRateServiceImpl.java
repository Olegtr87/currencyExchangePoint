package com.epam.vasilevsky.exchanger.service.impl;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import com.epam.vasilevsky.exchanger.dataaccess.ExchangeRateDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;

import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
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

	@Override
	public Long count(ExchangeRateFilter exchangeRateFilter) {
		LOGGER.info("Count for ExchangeRate perfomed!");
		return exchangeRateDao.count(exchangeRateFilter);
	}

	@Override
	public void saveOrUpdate(ExchangeRate exchangeRate) {
		if (exchangeRate.getId() == null) {
			exchangeRateDao.insert(exchangeRate);
		} else {
			exchangeRateDao.update(exchangeRate);
		}

	}
}
