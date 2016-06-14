package com.epam.vasilevsky.exchanger.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class BankAccountUser extends AbstractModel {
	@Column
	private Integer balance;
	@Column
	@Enumerated(value = EnumType.STRING)
	private CurrencyName currency;
	@ManyToOne(targetEntity = UserCredentials.class, fetch = FetchType.LAZY)
	private UserCredentials user;

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public CurrencyName getCurrency() {
		return currency;
	}
	
	public void setCurrency(CurrencyName currency) {
		this.currency = currency;
	}

	public UserCredentials getUser() {
		return user;
	}

	public void setUser(UserCredentials user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BankAccountUser [balance=" + balance + ", currency=" + currency + "]";
	}

}
