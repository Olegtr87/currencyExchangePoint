package by.epam.vasilevsky.exchanger.dataaccess.impl;

import org.springframework.stereotype.Repository;
import by.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import by.epam.vasilevsky.exchanger.datamodel.Currency;

@Repository
public class CurrencyDaoImpl extends AbstractDaoImpl<Currency,Long> implements CurrencyDao{

	protected CurrencyDaoImpl() {
		super(Currency.class);
	}

	

}
