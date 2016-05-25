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

import com.epam.vasilevsky.exchanger.dataaccess.ExchangeRateDao;
import com.epam.vasilevsky.exchanger.dataaccess.OperationDao;
import com.epam.vasilevsky.exchanger.dataaccess.TransactionDao;
import com.epam.vasilevsky.exchanger.dataaccess.UserCredentialsDao;
import com.epam.vasilevsky.exchanger.dataaccess.UserProfileDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;
import com.epam.vasilevsky.exchanger.dataaccess.impl.AbstractDaoImpl;
import com.epam.vasilevsky.exchanger.service.TransactionService;

import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Operation_;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.datamodel.Transaction_;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class TransactionServiceTest {

	@Inject
	TransactionService transactionService;
	
	@Inject
	TransactionDao transactionDao;
	
	@Inject
	ExchangeRateDao exchangeRateDao;
	
	@Inject
	OperationDao operationDao;
	
	@Inject
	UserCredentialsDao userCredentialsDao;
	
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
		Transaction transaction=new Transaction();
		transaction.setDateOperation(new Date());
		transaction.setSummIn(10000);
		ExchangeRate exchangeRate=exchangeRateDao.get((long) 28);
		Operation operation=operationDao.get((long) 3);
		UserCredentials userCredentials=userCredentialsDao.get((long) 3);
		transactionService.add(transaction, userCredentials, operation, exchangeRate);
				
		Assert.assertNotNull(transactionService.get(transaction.getId()));
	}
	
	@Test
	public void testUpdateTransaction(){
		Transaction transaction=new Transaction();
		transaction.setDateOperation(new Date());
		transaction.setSummIn(10000);
		ExchangeRate exchangeRate=exchangeRateDao.get((long) 28);
		Operation operation=operationDao.get((long) 3);
		UserCredentials userCredentials=userCredentialsDao.get((long) 3);
		transactionService.add(transaction, userCredentials, operation, exchangeRate);
		Integer updSum=999;
		transaction.setSummIn(updSum);
		transactionService.update(transaction);
		
		Assert.assertEquals(updSum, transactionDao.get(transaction.getId()).getSummIn());
	}
	
	@Test
	public void testDeleteTransaction(){
		Transaction transaction=new Transaction();
		transaction.setDateOperation(new Date());
		transaction.setSummIn(10000);
		ExchangeRate exchangeRate=exchangeRateDao.get((long) 28);
		Operation operation=operationDao.get((long) 3);
		UserCredentials userCredentials=userCredentialsDao.get((long) 3);
		transactionService.add(transaction, userCredentials, operation, exchangeRate);
		transactionService.delete(transaction.getId());
		
		Assert.assertNull(transactionService.get(transaction.getId()));
	}
	
	@Test
    public void testSearch() {
        // start create new data
		Transaction transaction=new Transaction();
		transaction.setDateOperation(new Date());
		transaction.setSummIn(10000);
		ExchangeRate exchangeRate=exchangeRateDao.get((long) 28);
		Operation operation=operationDao.get((long) 3);
		UserCredentials userCredentials=userCredentialsDao.get((long) 3);
		transactionService.add(transaction, userCredentials, operation, exchangeRate);

		TransactionFilter filter = new TransactionFilter();
        List<Transaction> result = transactionService.find(filter);
        // test paging
        filter.setFetchCredentials(true);
        filter.setDateTransaction(new Date());
        int limit = 3;
        filter.setLimit(limit);
        filter.setOffset(0);
        //result = currencyService.find(filter);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        // test sort
        filter.setLimit(null);
        filter.setOffset(null);
        filter.setSortOrder(true);
        filter.setSortProperty(Transaction_.dateOperation);
        result = transactionService.find(filter);
    }
}
