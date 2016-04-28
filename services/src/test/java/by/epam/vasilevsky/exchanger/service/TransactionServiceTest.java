package by.epam.vasilevsky.exchanger.service;

import java.lang.reflect.Field;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.epam.vasilevsky.exchanger.dataaccess.ExchangeRateDao;
import by.epam.vasilevsky.exchanger.dataaccess.OperationDao;
import by.epam.vasilevsky.exchanger.dataaccess.TransactionDao;
import by.epam.vasilevsky.exchanger.dataaccess.UserProfileDao;
import by.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;
import by.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;
import by.epam.vasilevsky.exchanger.dataaccess.impl.AbstractDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.datamodel.Operation;
import by.epam.vasilevsky.exchanger.datamodel.Operation_;
import by.epam.vasilevsky.exchanger.datamodel.Transaction;
import by.epam.vasilevsky.exchanger.datamodel.Transaction_;
import by.epam.vasilevsky.exchanger.datamodel.UserProfile;

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
	UserProfileDao userProfileDao;
	
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
		UserProfile userProfile=userProfileDao.get((long) 3);
		transactionService.add(transaction, userProfile, operation, exchangeRate);
				
		Assert.assertNotNull(transactionService.get(transaction.getId()));
	}
	
	@Test
	public void testUpdateTransaction(){
		Transaction transaction=new Transaction();
		transaction.setDateOperation(new Date());
		transaction.setSummIn(10000);
		ExchangeRate exchangeRate=exchangeRateDao.get((long) 28);
		Operation operation=operationDao.get((long) 3);
		UserProfile userProfile=userProfileDao.get((long) 3);
		transactionService.add(transaction, userProfile, operation, exchangeRate);
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
		UserProfile userProfile=userProfileDao.get((long) 3);
		transactionService.add(transaction, userProfile, operation, exchangeRate);
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
		UserProfile userProfile=userProfileDao.get((long) 3);
		transactionService.add(transaction, userProfile, operation, exchangeRate);

		TransactionFilter filter = new TransactionFilter();
        //List<Currency> result = currencyService.find(filter);
        // test paging
        filter.setFetchCredentials(true);
        //filter.setDateTransaction(new Date());
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
        //result = currencyService.find(filter);
    }
}
