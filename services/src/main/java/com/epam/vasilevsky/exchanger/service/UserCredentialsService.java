package com.epam.vasilevsky.exchanger.service;

import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.UserCredentialsFilter;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;

public interface UserCredentialsService {
	List<UserCredentials> find(UserCredentialsFilter filter);
}
