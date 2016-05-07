package com.epam.vasilevsky.exchanger.datamodel;

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
		this.dateCourse = dateCourse;
	}

	public Currency getCurrencyIdFrom() {
		return currencyFrom;
	}

	public void setCurrencyIdFrom(Currency currencyIdFrom) {
		this.currencyFrom = currencyIdFrom;
	}

	public Currency getCurrencyIdTo() {
		return currencyTo;
	}

	public void setCurrencyIdTo(Currency currencyIdTo) {
		this.currencyTo = currencyIdTo;
	}
	
	@Override
	public String toString() {
		return "ExchangeRate [id= "+getId()+" conversion=" + conversion + ", dateCourse=" + dateCourse;
	}
}
