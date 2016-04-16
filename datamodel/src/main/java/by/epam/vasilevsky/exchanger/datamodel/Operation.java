package by.epam.vasilevsky.exchanger.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Operation extends AbstractModel {
	@Column
	private String name;
	@Column
	private Boolean statusBlock;
	@Column
	private Double tax;
	@OneToMany(mappedBy = "operation", fetch = FetchType.LAZY)
    private List<Transaction> transaction;

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

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
