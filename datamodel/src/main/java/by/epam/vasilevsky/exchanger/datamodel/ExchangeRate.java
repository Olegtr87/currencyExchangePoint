package by.epam.vasilevsky.exchanger.datamodel;

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
	private Currency currency;
	@ManyToOne(targetEntity = Currency.class, fetch = FetchType.LAZY)
	private Currency currencyIdTo;

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
		return currency;
	}

	public void setCurrencyIdFrom(Currency currencyIdFrom) {
		this.currency = currencyIdFrom;
	}

	public Currency getCurrencyIdTo() {
		return currencyIdTo;
	}

	public void setCurrencyIdTo(Currency currencyIdTo) {
		this.currencyIdTo = currencyIdTo;
	}

}
