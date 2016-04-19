package by.epam.vasilevsky.exchanger.dataaccess;

import by.epam.vasilevsky.exchanger.datamodel.Transaction;

public interface TransactionDao {
	
	Transaction get(Long id);
	
	Transaction save();

}
