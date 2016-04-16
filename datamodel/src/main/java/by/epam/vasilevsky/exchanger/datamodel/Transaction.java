package by.epam.vasilevsky.exchanger.datamodel;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Transaction extends AbstractModel {

	private UserCredentials userId;
	private Operation operationId;
	private ExchangeRate exchangeRateId;
	private Integer summIn;
	private Date dateOperation;

	public UserCredentials getUserId() {
		return userId;
	}

	public void setUserId(UserCredentials userId) {
		this.userId = userId;
	}

	public Operation getOperationId() {
		return operationId;
	}

	public void setOperationId(Operation operationId) {
		this.operationId = operationId;
	}

	public ExchangeRate getExchangeRateId() {
		return exchangeRateId;
	}

	public void setExchangeRateId(ExchangeRate exchangeRateId) {
		this.exchangeRateId = exchangeRateId;
	}

	public Integer getSummIn() {
		return summIn;
	}

	public void setSummIn(Integer summIn) {
		this.summIn = summIn;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

}
