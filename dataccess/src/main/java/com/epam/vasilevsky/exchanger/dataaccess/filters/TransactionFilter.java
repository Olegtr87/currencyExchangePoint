package com.epam.vasilevsky.exchanger.dataaccess.filters;

import java.util.Date;

import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;

public class TransactionFilter extends AbstractFilter{

	private Date dateTransaction;
	private Integer sumIn;
	private Integer totalSum;
	private UserCredentials userCredentials;
	private Long id;
	
	public Integer getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Integer totalSum) {
		this.totalSum = totalSum;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSumIn() {
		return sumIn;
	}

	public void setSumIn(Integer sumIn) {
		this.sumIn = sumIn;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	public Date getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
}
