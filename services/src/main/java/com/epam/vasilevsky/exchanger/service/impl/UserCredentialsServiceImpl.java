package com.epam.vasilevsky.exchanger.service.impl;

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
	public List<UserCredentials> find(UserCredentialsFilter filter) {
		LOGGER.info("Search for UserCredentials perfomed!");
		return userCredentialsDao.find(filter);
	}

}
