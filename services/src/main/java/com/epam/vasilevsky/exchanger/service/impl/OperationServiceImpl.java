package com.epam.vasilevsky.exchanger.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.vasilevsky.exchanger.dataaccess.OperationDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;
import com.epam.vasilevsky.exchanger.service.OperationService;

import com.epam.vasilevsky.exchanger.datamodel.Operation;

@Service
public class OperationServiceImpl implements OperationService {
	private static Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);
	
	@Inject
	OperationDao operationDao;

	@Override
	public void add(Operation operation) {
		operationDao.insert(operation);
		LOGGER.info("Operation {} added", operation);
	}

	@Override
	public Operation get(Long id) {
		return operationDao.get(id);
	}

	@Override
	public void update(Operation operation) {
		operationDao.update(operation);
		LOGGER.info("Operation {} updated", operation);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Operation {} deleted", operationDao.get(id));
		operationDao.delete(id);
	}

	@Override
	public List<Operation> find(OperationFilter filter) {
		LOGGER.info("Search for Operation perfomed!");
		return operationDao.find(filter);
	}

	@Override
	public Long count(OperationFilter filter) {
		LOGGER.info("Count for Operation perfomed!");
		return operationDao.count(filter);
	}

	@Override
	public void saveOrUpdate(Operation operation) {
		if (operation.getId() == null) {
            operationDao.insert(operation);
        } else {
            operationDao.update(operation);
        }
	}

}
