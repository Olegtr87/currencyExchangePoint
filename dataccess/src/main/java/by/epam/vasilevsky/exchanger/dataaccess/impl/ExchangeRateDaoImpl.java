package by.epam.vasilevsky.exchanger.dataaccess.impl;

import org.springframework.stereotype.Repository;
import by.epam.vasilevsky.exchanger.dataaccess.ExchangeRateDao;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;

@Repository
public class ExchangeRateDaoImpl extends AbstractDaoImpl<ExchangeRate,Long> implements ExchangeRateDao{

	protected ExchangeRateDaoImpl() {
		super(ExchangeRate.class);
		// TODO Auto-generated constructor stub
	}

	
}
