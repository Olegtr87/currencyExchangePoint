package by.epam.vasilevsky.exchanger.datamodel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Currency extends AbstractModel {	
	@Column
	@Enumerated(value = EnumType.STRING)
	private CurrencyName name;
	@MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(nullable = false, updatable = false, name = "id")
	private Balance balance;
	@OneToMany(mappedBy = "currency", fetch = FetchType.LAZY)
    private List<ExchangeRate> exchangeRate;
	
	public List<ExchangeRate> getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(List<ExchangeRate> exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public CurrencyName getName() {
		return name;
	}

	public void setName(CurrencyName name) {
		this.name = name;
	}	
}
