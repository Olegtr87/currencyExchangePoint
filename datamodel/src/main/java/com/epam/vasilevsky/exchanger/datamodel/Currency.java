package com.epam.vasilevsky.exchanger.datamodel;

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
	
	public CurrencyName getName() {
		return name;
	}

	public void setName(CurrencyName name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Currency [name=" + name + "]";
	}	

}
