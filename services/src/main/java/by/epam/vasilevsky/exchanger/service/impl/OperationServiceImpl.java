package by.epam.vasilevsky.exchanger.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.epam.vasilevsky.exchanger.dataaccess.impl.OperationDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Operation;
import by.epam.vasilevsky.exchanger.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
	private static Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);
	
	@Inject
	OperationDaoImpl operationDaoImpl;

	@Override
	public void add(Operation operation) {
		operationDaoImpl.insert(operation);
		LOGGER.info("Operation {} added", operation);
	}

	@Override
	public Operation get(Long id) {
		return operationDaoImpl.get(id);
	}

	@Override
	public void update(Operation operation) {
		operationDaoImpl.update(operation);
		LOGGER.info("Operation {} updated", operation);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Operation {} deleted", operationDaoImpl.get(id));
		operationDaoImpl.delete(id);
	}

}
