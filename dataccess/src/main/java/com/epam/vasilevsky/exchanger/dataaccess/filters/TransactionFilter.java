package com.epam.vasilevsky.exchanger.dataaccess.filters;

import java.util.Date;

public class TransactionFilter extends AbstractFilter{

	private Date dateTransaction;
	private Integer sumIn;
	

	public Date getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
}
