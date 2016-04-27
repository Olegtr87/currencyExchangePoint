package by.epam.vasilevsky.exchanger.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import by.epam.vasilevsky.exchanger.dataaccess.UserCredentialsDao;
import by.epam.vasilevsky.exchanger.dataaccess.UserProfileDao;
import by.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;
import by.epam.vasilevsky.exchanger.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Inject
    private UserProfileDao userProfileDao;

    @Inject
    private UserCredentialsDao userCredentialsDao;

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
}