package com.epam.vasilevsky.exchanger.service;

import java.util.List;
import com.epam.vasilevsky.exchanger.dataaccess.filters.BalanceFilter;
import com.epam.vasilevsky.exchanger.datamodel.Balance;

public interface BalanceService {
	
	List<Balance> find(BalanceFilter filter);

	long count(BalanceFilter balanceFilter);
}

