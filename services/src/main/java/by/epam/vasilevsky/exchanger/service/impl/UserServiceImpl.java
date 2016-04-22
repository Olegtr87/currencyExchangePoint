package by.epam.vasilevsky.exchanger.service.impl;

import java.util.Date;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import by.epam.vasilevsky.exchanger.dataaccess.UserCredentialsDao;
import by.epam.vasilevsky.exchanger.dataaccess.UserProfileDao;
import by.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;
import by.epam.vasilevsky.exchanger.service.UserService;

@Service
public class UserServiceImpl implements UserService {
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
    }

    @Override
    public void delete(Long id) {
        userProfileDao.delete(id);
        userCredentialsDao.delete(id);
    }

	@Override
	public void updateCredentials(UserCredentials userCredentials) {
		userCredentialsDao.update(userCredentials);
		
	}
}