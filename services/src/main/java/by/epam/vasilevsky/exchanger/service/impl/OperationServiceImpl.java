package by.epam.vasilevsky.exchanger.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.epam.vasilevsky.exchanger.dataaccess.impl.OperationDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Operation;
import by.epam.vasilevsky.exchanger.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	@Inject
	OperationDaoImpl operationDaoImpl;

	@Override
	public void add(Operation operation) {
		operationDaoImpl.insert(operation);
	}

	@Override
	public Operation get(Long id) {
		return operationDaoImpl.get(id);
	}

	@Override
	public void update(Operation operation) {
		operationDaoImpl.update(operation);
	}

	@Override
	public void delete(Long id) {
		operationDaoImpl.delete(id);
	}

}
