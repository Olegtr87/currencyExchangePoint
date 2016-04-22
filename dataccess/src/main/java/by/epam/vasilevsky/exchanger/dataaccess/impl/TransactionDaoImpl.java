package by.epam.vasilevsky.exchanger.dataaccess.impl;

import org.springframework.stereotype.Repository;
import by.epam.vasilevsky.exchanger.dataaccess.TransactionDao;
import by.epam.vasilevsky.exchanger.datamodel.Transaction;

@Repository
public class TransactionDaoImpl extends AbstractDaoImpl<Transaction,Long> implements TransactionDao{

	protected TransactionDaoImpl() {
		super(Transaction.class);
	}

	

}
