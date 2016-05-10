package com.epam.vasilevsky.exchanger.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.vasilevsky.exchanger.dataaccess.filters.UserCredentialsFilter;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UserCredentialsServiceTest {
	
	@Inject
	private UserCredentialsService userService;

	@Test
	public void tesUC() {
		UserCredentialsFilter filter = new UserCredentialsFilter();
		filter.setLogin("login");
		filter.setPassword("password");
		List<UserCredentials> result = userService.find(filter);

		for (UserCredentials u : result) {
			System.out.println(u.toString());
		}
	}
}
