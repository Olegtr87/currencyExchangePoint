package by.epam.vasilevsky.exchanger.datamodel;

//import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;

@Entity
public class Currency extends AbstractModel {	
	@Column
	@Enumerated(value = EnumType.STRING)
	private CurrencyName name;
	
//	@OneToMany(mappedBy = "currencyTo", fetch = FetchType.LAZY)
//    private List<ExchangeRate> exchangeRate;
//	
//	public List<ExchangeRate> getExchangeRate() {
//		return exchangeRate;
//	}
//
//	public void setExchangeRate(List<ExchangeRate> exchangeRate) {
//		this.exchangeRate = exchangeRate;
//	}

	public CurrencyName getName() {
		return name;
	}

	public void setName(CurrencyName name) {
		this.name = name;
	}	
}
