package by.epam.vasilevsky.exchanger.dataaccess;

import by.epam.vasilevsky.exchanger.datamodel.UserCredentials;

public interface UserCredentialsDao {
	
	UserCredentials get(Long id);
	
	UserCredentials save();

}