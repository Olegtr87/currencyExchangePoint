package com.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;

import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;

public interface ExchangeRateDao extends AbstractDao<ExchangeRate, Long>{
	
	List<ExchangeRate> find(ExchangeRateFilter filter);
}
