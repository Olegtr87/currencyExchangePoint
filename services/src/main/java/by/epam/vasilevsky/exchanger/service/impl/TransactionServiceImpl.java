package by.epam.vasilevsky.exchanger.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.epam.vasilevsky.exchanger.dataaccess.impl.TransactionDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.datamodel.Operation;
import by.epam.vasilevsky.exchanger.datamodel.Transaction;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;
import by.epam.vasilevsky.exchanger.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Inject
	TransactionDaoImpl transactionDaoImpl;
		
	@Override
	public void add(Transaction transaction, UserProfile userProfile, Operation operation, ExchangeRate exchangeRate) {
		transaction.setUserId(userProfile);
		transaction.setOperationId(operation);
		transaction.setExchangeRateId(exchangeRate);
		transaction.setDateOperation(new Date());
		transactionDaoImpl.insert(transaction);	
	}

	@Override
	public Transaction get(Long id) {
		return transactionDaoImpl.get(id);		
	} 
}