package com.epam.vasilevsky.exchanger.service.impl;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.vasilevsky.exchanger.dataaccess.BankAccountUserDao;
import com.epam.vasilevsky.exchanger.dataaccess.UserCredentialsDao;
import com.epam.vasilevsky.exchanger.dataaccess.UserProfileDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.BankAccountUserFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.UserFilter;
import com.epam.vasilevsky.exchanger.service.BankUserAccountService;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.datamodel.UserRole;

@Service
public class BankUserAccountServiceImpl implements BankUserAccountService {
	private static Logger LOGGER = LoggerFactory.getLogger(BankUserAccountServiceImpl.class);
	
    @Inject
    private BankAccountUserDao bankAccountUserDao;

	@Override
	public List<BankAccountUser> findBankUserAccount(BankAccountUserFilter filter) {
		LOGGER.info("Search for AccountUser perfomed!");
		return bankAccountUserDao.find(filter);
	}

	@Override
	public Long count(BankAccountUserFilter filter) {
		LOGGER.info("Count for BankAccountUser perfomed!");
		return bankAccountUserDao.count(filter);
	}

	@Override
	public void update(BankAccountUser bankAccountUser) {
		bankAccountUserDao.update(bankAccountUser);
		LOGGER.info("Update for BankAccountUser perfomed!");
	}

	@Override
	public void add(BankAccountUser bankAccountUser) {
		LOGGER.info("BankAccountUser is added!");
		bankAccountUserDao.insert(bankAccountUser);
	}
}