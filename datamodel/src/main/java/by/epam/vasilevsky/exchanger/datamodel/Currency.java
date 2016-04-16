package by.epam.vasilevsky.exchanger.datamodel;

import javax.persistence.Entity;

@Entity
public class Currency extends AbstractModel {

	private String name;
	private Balance balance;
	
	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
