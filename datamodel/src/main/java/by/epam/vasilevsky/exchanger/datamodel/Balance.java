package by.epam.vasilevsky.exchanger.datamodel;

import javax.persistence.Entity;

@Entity
public class Balance extends AbstractModel {

	private Integer sum;
	private Currency currency;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

}
