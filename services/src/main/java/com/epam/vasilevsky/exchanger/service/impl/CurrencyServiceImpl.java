package com.epam.vasilevsky.exchanger.service.impl;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.vasilevsky.exchanger.dataaccess.BalanceDao;
import com.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.CurrencyFilter;
import com.epam.vasilevsky.exchanger.service.CurrencyService;

import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.Currency;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

	@Inject
	CurrencyDao currencyDao;

	@Inject
	BalanceDao balanceDao;

	@Override
	public void add(Currency currency, Balance balance) {
			currencyDao.insert(currency);
			balance.setCurrency(currency);
			balanceDao.insert(balance);
			LOGGER.info("Currency: {} and Balance: {} added", currency, balance);
	}

	@Override
	public Currency getCurrency(Long id) {
		return currencyDao.get(id);
	}

	@Override
	public Balance getBalance(Long id) {
		return balanceDao.get(id);
	}

	@Override
	public void updateCurrency(Currency currency) {
		currencyDao.update(currency);
		LOGGER.info("Currency {} updated", currency);
	}

	@Override
	public void updateBalance(Balance balance) {
		balanceDao.update(balance);
		LOGGER.info("Balance {} updated", balance);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Currency {} and Balance {} deleted", currencyDao.get(id), balanceDao.get(id));
		balanceDao.delete(id);
		currencyDao.delete(id);
	}

	@Override
	public List<Currency> find(CurrencyFilter filter) {
		LOGGER.info("Search for Currency perfomed!");
		return currencyDao.find(filter);
	}

}
