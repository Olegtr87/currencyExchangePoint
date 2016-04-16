package by.epam.vasilevsky.exchanger.datamodel;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ExchangeRate extends AbstractModel {
	@Column
	private Double conversion;
	@Column
	private Date dateCourse;
	@ManyToOne(targetEntity = Currency.class, fetch = FetchType.LAZY)
	private Currency currencyIdFrom;
	@ManyToOne(targetEntity = Currency.class, fetch = FetchType.LAZY)
	private Currency currencyIdTo;
	@OneToMany(mappedBy = "exchange_rate", fetch = FetchType.LAZY)
    private List<Transaction> transaction;

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
		return currencyIdFrom;
	}

	public void setCurrencyIdFrom(Currency currencyIdFrom) {
		this.currencyIdFrom = currencyIdFrom;
	}

	public Currency getCurrencyIdTo() {
		return currencyIdTo;
	}

	public void setCurrencyIdTo(Currency currencyIdTo) {
		this.currencyIdTo = currencyIdTo;
	}

}
