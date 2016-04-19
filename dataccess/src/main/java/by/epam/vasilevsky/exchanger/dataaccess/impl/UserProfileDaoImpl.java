package by.epam.vasilevsky.exchanger.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.epam.vasilevsky.exchanger.dataaccess.UserProfileDao;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

@Repository
public class UserProfileDaoImpl extends AbstractDaoImpl implements UserProfileDao {

    @Override
    public UserProfile get(Long id) {
        return null;
    }

    @Override
    public UserProfile save() {
        return null;
    }

}