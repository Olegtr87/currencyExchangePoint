package by.epam.vasilevsky.exchanger.service;

import javax.transaction.Transactional;

import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.datamodel.Operation;
import by.epam.vasilevsky.exchanger.datamodel.Transaction;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

public interface TransactionService {
	
	@Transactional
	void add(Transaction transaction, UserProfile userProfile, Operation operation, ExchangeRate exchangeRate);
	
	Transaction get(Long id);
}