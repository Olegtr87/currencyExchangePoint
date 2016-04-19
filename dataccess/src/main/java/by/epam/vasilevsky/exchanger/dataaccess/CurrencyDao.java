package by.epam.vasilevsky.exchanger.dataaccess;

import by.epam.vasilevsky.exchanger.datamodel.Currency;

public interface CurrencyDao {
	
	Currency get(Long id);
	
	Currency save();

}
