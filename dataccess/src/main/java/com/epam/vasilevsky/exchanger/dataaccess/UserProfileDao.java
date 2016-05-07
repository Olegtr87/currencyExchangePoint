package com.epam.vasilevsky.exchanger.dataaccess;


import java.util.List;

import com.epam.vasilevsky.exchanger.dataaccess.filters.UserFilter;

import com.epam.vasilevsky.exchanger.datamodel.UserProfile;

public interface UserProfileDao extends AbstractDao<UserProfile, Long>{
	
	List<UserProfile> find(UserFilter filter);
}