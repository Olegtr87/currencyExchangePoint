package com.epam.vasilevsky.exchanger.dataaccess.filters;

public class UserFilter extends AbstractFilter{

	private String userName;
	private String userLastName;
	private String numberPassport;
	private String patronymic;
	private String login;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getNumberPassport() {
		return numberPassport;
	}

	public void setNumberPassport(String numberPassport) {
		this.numberPassport = numberPassport;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
