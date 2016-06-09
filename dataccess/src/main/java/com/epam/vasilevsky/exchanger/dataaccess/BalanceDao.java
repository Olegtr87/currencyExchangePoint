package com.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.BalanceFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;
import com.epam.vasilevsky.exchanger.datamodel.Balance;

public interface BalanceDao extends AbstractDao<Balance, Long>{
	
	List<Balance> find(BalanceFilter filter);
	
	Long count(BalanceFilter filter);
}
