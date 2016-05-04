package by.epam.vasilevsky.exchanger.dataaccess.filters;

import java.util.Date;

public class ExchangeRateFilter extends AbstractFilter {

	private Date dateCurrency;
	private Integer sumIn;

	public Integer getSumIn() {
		return sumIn;
	}

	public void setSumIn(Integer sumIn) {
		this.sumIn = sumIn;
	}

	public Date getDateCurrency() {
		return dateCurrency;
	}

	public void setDateCurrency(Date dateCurrency) {
		this.dateCurrency = dateCurrency;
	}
}
