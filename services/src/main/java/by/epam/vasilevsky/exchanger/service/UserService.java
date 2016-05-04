package by.epam.vasilevsky.exchanger.service;

import java.util.List;

import javax.transaction.Transactional;

import by.epam.vasilevsky.exchanger.dataaccess.filters.UserFilter;
import by.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

public interface UserService {

	@Transactional
    void register(UserProfile userProfile, UserCredentials userCredentials);

    UserProfile getProfile(Long id);

    UserCredentials getCredentials(Long id);
    
    List<UserProfile> getAll();

    @Transactional
    void updateProfile(UserProfile userProfile);
    
    @Transactional
    void updateCredentials(UserCredentials userCredentials);

    @Transactional
    void delete(Long id);
    
    @Transactional
    List<UserProfile> find(UserFilter filter);
}