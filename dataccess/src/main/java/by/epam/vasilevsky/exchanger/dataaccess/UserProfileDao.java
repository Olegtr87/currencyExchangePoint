package by.epam.vasilevsky.exchanger.dataaccess;

import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

public interface UserProfileDao {

    UserProfile get(Long id);

    UserProfile save();

}