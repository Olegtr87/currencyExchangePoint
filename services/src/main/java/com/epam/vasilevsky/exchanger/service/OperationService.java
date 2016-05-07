package com.epam.vasilevsky.exchanger.service;
import java.util.List;
import javax.transaction.Transactional;

import com.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;

import com.epam.vasilevsky.exchanger.datamodel.Operation;

public interface OperationService {
	
	@Transactional
	void add(Operation operation);
	
	Operation get(Long id);
	
	@Transactional
    void update(Operation operation);

    @Transactional
    void delete(Long id);
    
    @Transactional
    List<Operation> find(OperationFilter filter);
}
