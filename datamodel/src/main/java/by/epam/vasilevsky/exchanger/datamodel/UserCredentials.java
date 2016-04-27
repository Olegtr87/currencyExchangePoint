package by.epam.vasilevsky.exchanger.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class UserCredentials extends AbstractModel {
	
	@Column(updatable = false)
	private String login;
	@Column
	private String password;
	@Column
	@Enumerated(value = EnumType.STRING)
	private UserRole role;	

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
	
	@Override
	public String toString() {
		return "UserCredentials [login=" + login + ", password=" + password + ", role=" + role + "]";
	}
}
