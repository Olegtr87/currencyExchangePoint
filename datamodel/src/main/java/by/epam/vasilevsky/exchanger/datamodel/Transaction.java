package by.epam.vasilevsky.exchanger.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Transaction extends AbstractModel {
	@ManyToOne(targetEntity = UserCredentials.class, fetch = FetchType.LAZY)
	private UserCredentials userId;
	@ManyToOne(targetEntity = Operation.class, fetch = FetchType.LAZY)
	private Operation operationId;
	@ManyToOne(targetEntity = ExchangeRate.class, fetch = FetchType.LAZY)
	private ExchangeRate exchangeRateId;
	@Column
	private Integer summIn;
	@Column
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
