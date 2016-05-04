package by.epam.vasilevsky.exchanger.service;

import java.util.List;
import javax.transaction.Transactional;
import by.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;

public interface ExchangeRateService {
	
	@Transactional
	void add(ExchangeRate exchangeRate, Currency currencyIdFrom, Currency currencyIdTo);
	
	ExchangeRate get(Long id);
	
	@Transactional
    void update(ExchangeRate exchangeRate);

    @Transactional
    void delete(Long id);
    
    @Transactional
    List<ExchangeRate> find(ExchangeRateFilter filter);
}
