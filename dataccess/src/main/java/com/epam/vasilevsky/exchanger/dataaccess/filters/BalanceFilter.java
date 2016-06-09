package com.epam.vasilevsky.exchanger.dataaccess.filters;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;

public class BalanceFilter extends AbstractFilter{
	
	private Integer sum;

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

}
