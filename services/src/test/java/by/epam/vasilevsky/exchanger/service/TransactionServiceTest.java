package by.epam.vasilevsky.exchanger.service;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.epam.vasilevsky.exchanger.dataaccess.TransactionDao;
import by.epam.vasilevsky.exchanger.dataaccess.impl.AbstractDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class TransactionServiceTest {

	@Inject
	TransactionService transactionService;
	
	@Inject
	TransactionDao transactionDao;
	
	@Test
	public void test() {
		Assert.assertNotNull(transactionService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(transactionDao);

		Assert.assertNotNull(em);
	}
	
	@Test
	public void testAddTransaction(){
		
	}
	
	@Test
	public void testUpdateTransaction(){
		
	}
	
	@Test
	public void testDeleteTransaction(){
		
	}
}
