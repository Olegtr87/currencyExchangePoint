package com.epam.vasilevsky.exchanger.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.vasilevsky.exchanger.dataaccess.UserCredentialsDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.UserCredentialsFilter;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.service.UserCredentialsService;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private UserCredentialsDao userCredentialsDao;


	@Override
	public UserCredentials findByLoginAndPassword(String login, String password) {
		UserCredentialsFilter filter=new UserCredentialsFilter();
		filter.setLogin(login);
		filter.setPassword(password);
		return userCredentialsDao.find(filter);
	}
	
	@Override
	public UserCredentials findByLogin(String login) {
		UserCredentialsFilter filter=new UserCredentialsFilter();
		filter.setLogin(login);
		return userCredentialsDao.find(filter);
	}

	@Override
	public Collection<? extends String> resolveRoles(Long id) {
		UserCredentials userCredentials = userCredentialsDao.get(id);
        return Collections.singletonList(userCredentials.getRole().name());
	}

}
