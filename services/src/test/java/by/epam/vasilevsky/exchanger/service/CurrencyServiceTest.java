package by.epam.vasilevsky.exchanger.service;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import by.epam.vasilevsky.exchanger.dataaccess.impl.AbstractDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Balance;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.datamodel.CurrencyName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class CurrencyServiceTest {

	@Inject
	private CurrencyService currencyService;

	@Inject
	private CurrencyDao currencyDao;

	@Test
	public void test() {
		Assert.assertNotNull(currencyService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(currencyDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void testCurrencyAdd() {
		// Currency currency=new Currency();
		// Balance balance=new Balance();
		// currency.setName(CurrencyName.EUR);
		// balance.setCurrency(currency);
		// balance.setSum(1000);
		// currencyService.add(currency, balance);
		//
		// Assert.assertNotNull(currencyService.getCurrency(currency.getId()));
		// Assert.assertNotNull(currencyService.getBalance(balance.getId()));
	}

	@Test
	public void testCurrencyUpdate() {
		// Currency currency=new Currency();
		// Balance balance=new Balance();
		// currency.setName(CurrencyName.BRB);
		// balance.setCurrency(currency);
		// balance.setSum(1000);
		// currencyService.add(currency, balance);
		// Integer updateSumm=20000;
		// balance.setSum(updateSumm);
		// currencyService.updateBalance(balance);
		// Assert.assertEquals(updateSumm,
		// currencyService.getBalance(balance.getId()).getSum());
	}

	@Test
	public void testCurrencyDelete() {
//		Currency currency = new Currency();
//		Balance balance = new Balance();
//		currency.setName(CurrencyName.BRB);
//		balance.setCurrency(currency);
//		balance.setSum(1000);
//		currencyService.add(currency, balance);
//		currencyService.delete(balance.getId());
//
//		Assert.assertNull(currencyService.getBalance(balance.getId()));
//		Assert.assertNull(currencyService.getCurrency(currency.getId()));
	}
}
