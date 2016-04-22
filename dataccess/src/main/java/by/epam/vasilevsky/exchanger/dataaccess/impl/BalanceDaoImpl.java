package by.epam.vasilevsky.exchanger.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.epam.vasilevsky.exchanger.dataaccess.BalanceDao;
import by.epam.vasilevsky.exchanger.datamodel.Balance;

@Repository
public class BalanceDaoImpl extends AbstractDaoImpl<Balance,Long> implements BalanceDao{

	protected BalanceDaoImpl() {
		super(Balance.class);
	}

	

}
