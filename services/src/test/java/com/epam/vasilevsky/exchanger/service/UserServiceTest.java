package com.epam.vasilevsky.exchanger.service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.vasilevsky.exchanger.dataaccess.UserProfileDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.UserFilter;
import com.epam.vasilevsky.exchanger.dataaccess.impl.AbstractDaoImpl;
import com.epam.vasilevsky.exchanger.service.UserService;

import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile_;
import com.epam.vasilevsky.exchanger.datamodel.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UserServiceTest {

	@Inject
	private UserService userService;

	@Inject
	private UserProfileDao userProfileDao;

	@Test
	public void test() {
		Assert.assertNotNull(userService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(userProfileDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void testRegistrationUser() {
		UserProfile profile = new UserProfile();
		UserCredentials userCredentials = new UserCredentials();

		profile.setFirstName("testFName");
		profile.setLastName("testLName");
		profile.setPatronymic("otch");
		profile.setNumberPassport(System.currentTimeMillis() + "n");
		profile.setDateIssue(new Date());
		profile.setIssued("issued");

		userCredentials.setLogin(System.currentTimeMillis() + "m");
		userCredentials.setPassword("pswd");
		userCredentials.setRole(UserRole.Administrator);
		userService.register(profile, userCredentials);

		UserProfile registredProfile = userService.getProfile(profile.getId());
		UserCredentials registredCredentials = userService.getCredentials(userCredentials.getId());

		Assert.assertNotNull(registredProfile);
		Assert.assertNotNull(registredCredentials);
	}

	@Test
	public void testUpdateUser() {
		UserProfile profile = new UserProfile();
		UserCredentials userCredentials = new UserCredentials();

		profile.setFirstName("testFName111");
		profile.setLastName("testLName111");
		profile.setPatronymic("otch");
		profile.setNumberPassport(System.currentTimeMillis() + "n");
		profile.setDateIssue(new Date());
		profile.setIssued("issued");

		userCredentials.setLogin(System.currentTimeMillis() + "m");
		userCredentials.setPassword("pswd");
		userCredentials.setRole(UserRole.Administrator);
		userService.register(profile, userCredentials);
		
		String updatedProfileFName = "updatedName";		
		profile.setFirstName(updatedProfileFName);
		userService.updateProfile(profile);
		
		String updatedCredentialsPass = "updated";
		userCredentials.setPassword(updatedCredentialsPass);
		userService.updateCredentials(userCredentials);

		Assert.assertEquals(updatedProfileFName, userService.getProfile(profile.getId()).getFirstName());
		Assert.assertEquals(updatedCredentialsPass, userService.getCredentials(userCredentials.getId()).getPassword());

	}

	@Test
	public void testDeleteUser() {

		UserProfile profile = new UserProfile();
		UserCredentials userCredentials = new UserCredentials();

		profile.setFirstName("testFName111");
		profile.setLastName("testLName111");
		profile.setPatronymic("otch");
		profile.setNumberPassport(System.currentTimeMillis() + "n");
		profile.setDateIssue(new Date());
		profile.setIssued("issued");

		userCredentials.setLogin(System.currentTimeMillis() + "m");
		userCredentials.setPassword("pswd");
		userCredentials.setRole(UserRole.Administrator);
		userService.register(profile, userCredentials);

		userService.delete(profile.getId());
		Assert.assertNull(userService.getProfile(profile.getId()));
		Assert.assertNull(userService.getCredentials(userCredentials.getId()));
	}
	
	@Test
    public void testSearch() {
        // clean all data from users
        List<UserProfile> all = userService.getAll();
        for (UserProfile userProfile : all) {
            if (userProfile.getId()==3||userProfile.getId()==1) continue;
        	userService.delete(userProfile.getId());
        }
		

        // start create new data
        int testObjectsCount = 5;
        for (int i = 0; i < testObjectsCount; i++) {
            UserProfile profile = new UserProfile();
            UserCredentials userCredentials = new UserCredentials();

            profile.setFirstName("testFName111"+i);
    		profile.setLastName("testLName111"+i);
    		profile.setPatronymic("otch"+i);
    		profile.setNumberPassport(System.currentTimeMillis() + "n"+i);
    		profile.setDateIssue(new Date());
    		profile.setIssued("issued"+i);

            userCredentials.setLogin(i + "mail@test.by");
            userCredentials.setPassword(i + "pswd");
            userCredentials.setRole(UserRole.Administrator);
            userService.register(profile, userCredentials);
        }

        UserFilter filter = new UserFilter();
        List<UserProfile> result = userService.find(filter);
        Assert.assertEquals(testObjectsCount+2, result.size());
        // test paging
        filter.setFetchCredentials(true);
        //filter.setUserName("testLName");
        filter.setLogin("0mail@test.by");
        int limit = 3;
        filter.setLimit(limit);
        filter.setOffset(0);
        result = userService.find(filter);
        //Assert.assertEquals(limit, result.size());

        // test sort
        filter.setLimit(null);
        filter.setOffset(null);
        filter.setSortOrder(true);
        filter.setSortProperty(UserProfile_.firstName);
        result = userService.find(filter);
        
        for (UserProfile users:result){
        	System.out.println(users);
        }
        
        
        //Assert.assertEquals(testObjectsCount+2, result.size());

    }
}