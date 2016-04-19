package by.epam.vasilevsky.exchanger.service;

import by.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

public interface UserService {

    void register(UserProfile profile, UserCredentials userCredentials);
}