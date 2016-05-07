package com.epam.vasilevsky.exchanger.dataaccess.filters;

public class OperationFilter extends AbstractFilter{

	private String operationName;

    public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
}
