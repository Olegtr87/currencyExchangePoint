package by.epam.vasilevsky.exchanger.datamodel;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Transaction extends AbstractModel {
	
	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY)
	private UserProfile user;
	@ManyToOne(targetEntity = Operation.class, fetch = FetchType.LAZY)
	private Operation operation;
	@ManyToOne(targetEntity = ExchangeRate.class, fetch = FetchType.LAZY)
	private ExchangeRate exchangeRate;
	@Column
	private Integer sumIn;
	@Column
	private Date dateOperation;

	public UserProfile getUserId() {
		return user;
	}

	public void setUserId(UserProfile userId) {
		this.user = userId;
	}

	public Operation getOperationId() {
		return operation;
	}

	public void setOperationId(Operation operationId) {
		this.operation = operationId;
	}

	public ExchangeRate getExchangeRateId() {
		return exchangeRate;
	}

	public void setExchangeRateId(ExchangeRate exchangeRateId) {
		this.exchangeRate = exchangeRateId;
	}

	public Integer getSummIn() {
		return sumIn;
	}

	public void setSummIn(Integer summIn) {
		this.sumIn = summIn;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	@Override
	public String toString() {
		return "Transaction [ id"+getId()+" sumIn=" + sumIn + ", dateOperation=" + dateOperation + "]";
	}	 
	
	

}
