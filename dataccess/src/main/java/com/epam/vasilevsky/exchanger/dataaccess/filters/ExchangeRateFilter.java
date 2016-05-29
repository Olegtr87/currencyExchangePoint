package com.epam.vasilevsky.exchanger.dataaccess.filters;

import java.util.Calendar;
import java.util.Date;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;

public class ExchangeRateFilter extends AbstractFilter {

	private Date dateCourse;
	private CurrencyName currencyFrom;
	private CurrencyName currencyTo;
	private Double conversion;

	public CurrencyName getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(CurrencyName currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public CurrencyName getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(CurrencyName currencyTo) {
		this.currencyTo = currencyTo;
	}

	public Double getConversion() {
		return conversion;
	}

	public void setConversion(Double conversion) {
		this.conversion = conversion;
	}

	public Date getDateCourse() {
		return dateCourse;
	}

	public void setDateCourse(Date dateCurrency) {
		this.dateCourse = dateFormat(dateCurrency);
	}

	private Date dateFormat(Date dateCurrency) {
		// filter without time
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();
		date.setDate(dateCurrency.getDate());
		date.setYear(dateCurrency.getYear());
		date.setMonth(dateCurrency.getMonth());
		return date;
	}
}
