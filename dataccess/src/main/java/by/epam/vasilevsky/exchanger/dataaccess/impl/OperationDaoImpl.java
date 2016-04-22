package by.epam.vasilevsky.exchanger.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.epam.vasilevsky.exchanger.dataaccess.OperationDao;
import by.epam.vasilevsky.exchanger.datamodel.Operation;

@Repository
public class OperationDaoImpl extends AbstractDaoImpl<Operation,Long> implements OperationDao{

	protected OperationDaoImpl() {
		super(Operation.class);
	}

	

}
