package by.epam.vasilevsky.exchanger.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.epam.vasilevsky.exchanger.dataaccess.filters.CurrencyFilter;
import by.epam.vasilevsky.exchanger.dataaccess.impl.BalanceDaoImpl;
import by.epam.vasilevsky.exchanger.dataaccess.impl.CurrencyDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Balance;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
	
	@Inject
	CurrencyDaoImpl currencyDaoImpl;
	
	@Inject
	BalanceDaoImpl balanceDaoImpl;

	@Override
	public void add(Currency currency, Balance balance) {
		currencyDaoImpl.insert(currency);
		balance.setCurrency(currency);
		balanceDaoImpl.insert(balance);
		LOGGER.info("Currency: {} and Balance: {} added", currency,balance);
	}

	@Override
	public Currency getCurrency(Long id) {
		return currencyDaoImpl.get(id);
	}

	@Override
	public Balance getBalance(Long id) {
		return balanceDaoImpl.get(id);
	}

	@Override
	public void updateCurrency(Currency currency) {
		currencyDaoImpl.update(currency);
		LOGGER.info("Currency {} updated", currency);		
	}

	@Override
	public void updateBalance(Balance balance) {
		balanceDaoImpl.update(balance);
		LOGGER.info("Balance {} updated", balance);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Currency {} and Balance {} deleted", currencyDaoImpl.get(id), balanceDaoImpl.get(id));
		balanceDaoImpl.delete(id);
		currencyDaoImpl.delete(id);
	}

	@Override
	public List<Currency> find(CurrencyFilter filter) {
		LOGGER.info("Search for Currency perfomed!");
		return currencyDaoImpl.find(filter);
	}
	
}
