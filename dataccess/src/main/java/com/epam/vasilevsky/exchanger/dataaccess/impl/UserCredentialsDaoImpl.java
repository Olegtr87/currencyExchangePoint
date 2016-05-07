package com.epam.vasilevsky.exchanger.dataaccess.impl;

import org.springframework.stereotype.Repository;

import com.epam.vasilevsky.exchanger.dataaccess.UserCredentialsDao;

import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;

@Repository
public class UserCredentialsDaoImpl extends AbstractDaoImpl<UserCredentials, Long> implements UserCredentialsDao {

	protected UserCredentialsDaoImpl() {
		super(UserCredentials.class);
	}

	

}