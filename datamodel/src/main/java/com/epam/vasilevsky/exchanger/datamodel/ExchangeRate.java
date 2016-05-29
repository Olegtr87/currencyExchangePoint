package com.epam.vasilevsky.exchanger.datamodel;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ExchangeRate extends AbstractModel {

	@Column
	private Double conversion;
	@Column
	private Date dateCourse;
	@ManyToOne(targetEntity = Currency.class, fetch = FetchType.LAZY)
	private Currency currencyFrom;
	@ManyToOne(targetEntity = Currency.class, fetch = FetchType.LAZY)
	private Currency currencyTo;

	public Double getConversion() {
		return conversion;
	}

	public void setConversion(Double conversion) {
		this.conversion = conversion;
	}

	public Date getDateCourse() {
		return dateCourse;
	}

	public void setDateCourse(Date dateCourse) {
		this.dateCourse = dateFormat(dateCourse);
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

	public Currency getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	@Override
	public String toString() {
		return "ExchangeRate [id= " + getId() + " conversion=" + getConversion() + ", dateCourse=" + getDateCourse();
	}
}
