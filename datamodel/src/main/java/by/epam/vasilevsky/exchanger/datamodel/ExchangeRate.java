package by.epam.vasilevsky.exchanger.datamodel;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class ExchangeRate extends AbstractModel {

	private Double conversion;
	private Date dateCourse;
	private Currency currencyIdFrom;
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
