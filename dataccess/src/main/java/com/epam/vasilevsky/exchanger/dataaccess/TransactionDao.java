package com.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;

import com.epam.vasilevsky.exchanger.datamodel.Transaction;

public interface TransactionDao extends AbstractDao<Transaction, Long>{
	
	List<Transaction> find(TransactionFilter filter);
	
	Long count(TransactionFilter filter);
}
