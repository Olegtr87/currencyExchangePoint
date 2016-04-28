package by.epam.vasilevsky.exchanger.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import by.epam.vasilevsky.exchanger.dataaccess.TransactionDao;
import by.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.datamodel.Operation;
import by.epam.vasilevsky.exchanger.datamodel.Transaction;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;
import by.epam.vasilevsky.exchanger.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	private static Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Inject
	TransactionDao transactionDao;
		
	@Override
	public void add(Transaction transaction, UserProfile userProfile, Operation operation, ExchangeRate exchangeRate) {
		transaction.setUserId(userProfile);
		transaction.setOperationId(operation);
		transaction.setExchangeRateId(exchangeRate);
		transaction.setDateOperation(new Date());
		transactionDao.insert(transaction);	
		LOGGER.info("Transaction {} added", transaction);
	}

	@Override
	public Transaction get(Long id) {
		return transactionDao.get(id);		
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Transaction {} deleted",transactionDao.get(id));
		transactionDao.delete(id);
	} 
	
	@Override
	public void update(Transaction transaction) {
		transactionDao.update(transaction);
		LOGGER.info("Transaction {} updated", transaction);
	}

	@Override
	public List<Transaction> find(TransactionFilter filter) {
		LOGGER.info("Search for Transaction perfomed!");
		return transactionDao.find(filter);
	} 
	
}