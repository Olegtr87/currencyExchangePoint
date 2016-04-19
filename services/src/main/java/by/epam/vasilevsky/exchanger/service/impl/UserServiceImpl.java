package by.epam.vasilevsky.exchanger.service.impl;

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
    public void register(UserProfile profile, UserCredentials userCredentials) {
        // TODO Auto-generated method stub
    }

}