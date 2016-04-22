package by.epam.vasilevsky.exchanger.service;

import javax.transaction.Transactional;

import by.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

public interface UserService {

	@Transactional
    void register(UserProfile userProfile, UserCredentials userCredentials);

    UserProfile getProfile(Long id);

    UserCredentials getCredentials(Long id);

    @Transactional
    void updateProfile(UserProfile userProfile);
    
    @Transactional
    void updateCredentials(UserCredentials userCredentials);

    @Transactional
    void delete(Long id);
}