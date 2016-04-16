package by.epam.vasilevsky.exchanger.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Balance extends AbstractModel {
	@Column
	private Integer sum;
	
	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

}
