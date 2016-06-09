package com.epam.vasilevsky.exchanger.service.impl;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.vasilevsky.exchanger.dataaccess.BalanceDao;
import com.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.BalanceFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.CurrencyFilter;
import com.epam.vasilevsky.exchanger.service.BalanceService;
import com.epam.vasilevsky.exchanger.service.CurrencyService;

import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.Currency;

@Service
public class BalanceServiceImpl implements BalanceService {
	private static Logger LOGGER = LoggerFactory.getLogger(BalanceServiceImpl.class);

	@Inject
	BalanceDao balanceDao;

	@Override
	public List<Balance> find(BalanceFilter filter) {
		LOGGER.info("Search for Currency perfomed!");
		return balanceDao.find(filter);
	}


	@Override
	public long count(BalanceFilter balanceFilter) {
		LOGGER.info("Count for Currency perfomed!");
		return balanceDao.count(balanceFilter);
	}

}
