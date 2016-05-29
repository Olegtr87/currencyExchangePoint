package com.epam.vasilevsky.exchanger.datamodel;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Transaction extends AbstractModel {
	
	@ManyToOne(targetEntity = UserCredentials.class, fetch = FetchType.LAZY)
	private UserCredentials user;
	@ManyToOne(targetEntity = Operation.class, fetch = FetchType.LAZY)
	private Operation operation;
	@ManyToOne(targetEntity = ExchangeRate.class, fetch = FetchType.LAZY)
	private ExchangeRate exchangeRate;
	@Column
	private Integer sumIn;
	@Column
	private Date dateOperation;

	public UserCredentials getUser() {
		return user;
	}

	public void setUser(UserCredentials user) {
		this.user = user;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Integer getSumIn() {
		return sumIn;
	}

	public void setSumIn(Integer summIn) {
		this.sumIn = summIn;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	@Override
	public String toString() {
		return "Transaction [ id"+getId()+" sumIn=" + sumIn + ", dateOperation=" + dateOperation + "]";
	}	 
	
	

}
