package com.epam.vasilevsky.exchanger.dataaccess.filters;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;

public class BankAccountUserFilter extends AbstractFilter{

	private CurrencyName currency;
	private Integer balance;
	private UserCredentials userCredentials;
	
	
	public UserCredentials getUserCredentials() {
		return userCredentials;
	}
	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
	public CurrencyName getCurrency() {
		return currency;
	}
	public void setCurrency(CurrencyName currency) {
		this.currency = currency;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	
}
