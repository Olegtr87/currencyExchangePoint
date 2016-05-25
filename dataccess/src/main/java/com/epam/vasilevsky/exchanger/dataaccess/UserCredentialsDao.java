package com.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.UserCredentialsFilter;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;

public interface UserCredentialsDao extends AbstractDao<UserCredentials, Long>{
	
	UserCredentials find(UserCredentialsFilter filter);
}