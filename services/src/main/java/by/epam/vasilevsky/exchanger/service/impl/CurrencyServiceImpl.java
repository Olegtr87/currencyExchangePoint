package by.epam.vasilevsky.exchanger.service.impl;

import javax.inject.Inject;

import by.epam.vasilevsky.exchanger.dataaccess.impl.BalanceDaoImpl;
import by.epam.vasilevsky.exchanger.dataaccess.impl.CurrencyDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Balance;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.service.CurrencyService;

public class CurrencyServiceImpl implements CurrencyService {

	@Inject
	CurrencyDaoImpl currencyDaoImpl;
	
	@Inject
	BalanceDaoImpl balanceDaoImpl;

	@Override
	public void add(Currency currency, Balance balance) {
		currencyDaoImpl.insert(currency);
		balance.setCurrency(currency);
		balanceDaoImpl.insert(balance);		
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
	}

	@Override
	public void updateBalance(Balance balance) {
		balanceDaoImpl.update(balance);
	}

	@Override
	public void delete(Long id) {
		balanceDaoImpl.delete(id);
		currencyDaoImpl.delete(id);
	}
	
}
