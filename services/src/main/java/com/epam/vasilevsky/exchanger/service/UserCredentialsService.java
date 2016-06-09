package com.epam.vasilevsky.exchanger.service;

import java.util.Collection;
import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.UserCredentialsFilter;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;

public interface UserCredentialsService {

	UserCredentials findByLoginAndPassword(String login, String password);

	Collection<? extends String> resolveRoles(Long id);

	UserCredentials findByLogin(String login);
}
