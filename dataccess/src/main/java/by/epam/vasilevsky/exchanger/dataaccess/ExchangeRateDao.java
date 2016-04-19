package by.epam.vasilevsky.exchanger.dataaccess;

import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;

public interface ExchangeRateDao {
	
	ExchangeRate get(Long id);
	
	ExchangeRate save();

}
