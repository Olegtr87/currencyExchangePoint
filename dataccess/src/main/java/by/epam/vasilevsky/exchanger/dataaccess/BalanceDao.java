package by.epam.vasilevsky.exchanger.dataaccess;

import by.epam.vasilevsky.exchanger.datamodel.Balance;

public interface BalanceDao {

	Balance get(Long id);
	
	Balance save();
}
