package by.epam.vasilevsky.exchanger.dataaccess;


import java.util.List;
import by.epam.vasilevsky.exchanger.dataaccess.filters.UserFilter;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

public interface UserProfileDao extends AbstractDao<UserProfile, Long>{
	
	List<UserProfile> find(UserFilter filter);
}