package by.epam.vasilevsky.exchanger.datamodel;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserProfile extends AbstractModel {
	@Column
	private String lastName;
	@Column
	private String firstName;
	@Column
	private String patronymic;
	@Column
	private String numberPassport;
	@Column
	private Date dateIssue;
	@Column
	private String issued;	
	@MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(nullable = false, updatable = false, name = "id")
	private UserCredentials userCredentials;	
	@Column
	private Date created;
//	@OneToMany(mappedBy = "user_profile", fetch = FetchType.LAZY)
//    private List<Transaction> transaction;
//	
//	public List<Transaction> getTransaction() {
//		return transaction;
//	}
//
//	public void setTransaction(List<Transaction> transaction) {
//		this.transaction = transaction;
//	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getNumberPassport() {
		return numberPassport;
	}

	public void setNumberPassport(String numberPassport) {
		this.numberPassport = numberPassport;
	}

	public Date getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(Date dateIssue) {
		this.dateIssue = dateIssue;
	}

	public String getIssued() {
		return issued;
	}

	public void setIssued(String issued) {
		this.issued = issued;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

}
