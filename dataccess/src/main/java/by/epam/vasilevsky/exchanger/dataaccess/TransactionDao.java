package by.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;

import by.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;
import by.epam.vasilevsky.exchanger.datamodel.Transaction;

public interface TransactionDao extends AbstractDao<Transaction, Long>{
	
	List<Transaction> find(TransactionFilter filter);
}
