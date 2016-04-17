package by.epam.vasilevsky.exchanger.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Operation extends AbstractModel {
	@Column
	private String name;
	@Column
	private Boolean statusBlock;
	@Column
	private Double tax;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatusBlock() {
		return statusBlock;
	}

	public void setStatusBlock(Boolean statusBlock) {
		this.statusBlock = statusBlock;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

}
