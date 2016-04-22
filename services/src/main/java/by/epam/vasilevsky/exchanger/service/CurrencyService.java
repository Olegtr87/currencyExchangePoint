package by.epam.vasilevsky.exchanger.service;

import javax.transaction.Transactional;

import by.epam.vasilevsky.exchanger.datamodel.Balance;
import by.epam.vasilevsky.exchanger.datamodel.Currency;

public interface CurrencyService {
	@Transactional
    void add(Currency currency, Balance balance);

	Currency getCurrency(Long id);

    Balance getBalance(Long id);

    @Transactional
    void updateCurrency(Currency currency);
    
    @Transactional
    void updateBalance(Balance balance);

    @Transactional
    void delete(Long id);
}
