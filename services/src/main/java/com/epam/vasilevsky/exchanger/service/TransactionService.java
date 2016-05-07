package com.epam.vasilevsky.exchanger.service;

import java.util.List;
import javax.transaction.Transactional;

import com.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;

import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;

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