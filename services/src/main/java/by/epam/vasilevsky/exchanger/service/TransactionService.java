package by.epam.vasilevsky.exchanger.service;

import java.util.List;
import javax.transaction.Transactional;
import by.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.datamodel.Operation;
import by.epam.vasilevsky.exchanger.datamodel.Transaction;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

public interface TransactionService {
	
	@Transactional
	void add(Transaction transaction, UserProfile userProfile, Operation operation, ExchangeRate exchangeRate);
	
	Transaction get(Long id);
	
	@Transactional
    void delete(Long id);
	
	@Transactional
    void update(Transaction transaction);
	
	@Transactional
	List<Transaction> find(TransactionFilter filter);
}