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
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.datamodel.UserRole;

@Service
public class UserServiceImpl implements UserService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Inject
    private UserProfileDao userProfileDao;

    @Inject
    private UserCredentialsDao userCredentialsDao;
    
    @Inject
    private BankAccountUserDao bankAccountUserDao;

    @Override
    public void register(UserProfile userProfile, UserCredentials userCredentials) {
        userCredentialsDao.insert(userCredentials);
        userProfile.setUserCredentials(userCredentials);
        userProfile.setCreated(new Date());
        userProfileDao.insert(userProfile);
        LOGGER.info("User regirstred: {}", userCredentials);
    }

    @Override
    public UserProfile getProfile(Long id) {
    	return userProfileDao.get(id);
        
    }

    @Override
    public UserCredentials getCredentials(Long id) {
    	return userCredentialsDao.get(id);
    }

    @Override
    public void updateProfile(UserProfile userProfile) {    	
        userProfileDao.update(userProfile);
        LOGGER.info("UserProfile {} updated",userProfile);
    }

    @Override
    public void delete(Long id) {
    	LOGGER.info("UserCredentials and UserProfile {} is deleted",userCredentialsDao.get(id),userProfileDao.get(id));
    	userProfileDao.delete(id);
        userCredentialsDao.delete(id);        
    }

	@Override
	public void updateCredentials(UserCredentials userCredentials) {
		userCredentialsDao.update(userCredentials);
		LOGGER.info("UserCredentials {} updated",userCredentials);		
	}

	@Override
	public List<UserProfile> find(UserFilter filter) {
		LOGGER.info("Search for User perfomed!");
		return userProfileDao.find(filter);
	}

	@Override
	public List<UserProfile> getAll() {
		return userProfileDao.getAll();
	}
}