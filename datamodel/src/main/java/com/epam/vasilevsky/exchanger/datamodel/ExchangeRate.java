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
		// add date without time
		dateCourse.setHours(0);
		dateCourse.setMinutes(0);
		dateCourse.setSeconds(0);
		this.dateCourse = dateCourse;
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
