package by.epam.vasilevsky.exchanger.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class UserCredentials extends AbstractModel {
	@Column
	private String login;
	@Column
	private String password;
	@Column
	@Enumerated(value = EnumType.STRING)
	private UserRole role;
	@OneToMany(mappedBy = "user_credentials", fetch = FetchType.LAZY)
    private List<Transaction> transaction;
	
	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
