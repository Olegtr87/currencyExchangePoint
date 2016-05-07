package com.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;

import com.epam.vasilevsky.exchanger.datamodel.Operation;

public interface OperationDao extends AbstractDao<Operation, Long>{

	List<Operation> find(OperationFilter filter);
}
