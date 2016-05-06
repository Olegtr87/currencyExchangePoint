package by.epam.vasilevsky.exchanger.service;

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
import by.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import by.epam.vasilevsky.exchanger.dataaccess.ExchangeRateDao;
import by.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import by.epam.vasilevsky.exchanger.dataaccess.impl.AbstractDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate_;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class ExchangeRateServiceTest {
		
	@Inject
	private ExchangeRateService exchangeRateService;

	@Inject
	private ExchangeRateDao exchangeRateDao;
	
	@Inject
	CurrencyDao currencyDao;

	@Test
	public void test() {
		Assert.assertNotNull(exchangeRateService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(exchangeRateDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void testExchangeRateAdd() {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setDateCourse(new Date());
		exchangeRate.setConversion(1.5);
		Currency currencyFrom = currencyDao.get((long) 8);
		Currency currencyTo = currencyDao.get((long) 8);
		exchangeRateService.add(exchangeRate, currencyFrom, currencyTo);

		Assert.assertNotNull(exchangeRateDao.get(exchangeRate.getId()).getId());
	}

	@Test
	public void testExchangeRateUpdate() {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setDateCourse(new Date());
		exchangeRate.setConversion(1.5);
		Currency currency = currencyDao.get((long) 8);
		Currency currency1 = currencyDao.get((long) 8);
		exchangeRateService.add(exchangeRate, currency, currency1);
		Double upd = 1.99999999;
		exchangeRate.setConversion(upd);
		exchangeRateService.update(exchangeRate);

		Assert.assertEquals(upd, exchangeRate.getConversion());
	}

	@Test
	public void testExchangeRateDelete() {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setDateCourse(new Date());
		exchangeRate.setConversion(1.11111111111111);
		Currency currency = currencyDao.get((long) 8);
		Currency currency1 = currencyDao.get((long) 8);
		exchangeRateService.add(exchangeRate, currency, currency1);
		exchangeRateService.delete(exchangeRateDao.get(exchangeRate.getId()).getId());

		Assert.assertNull(exchangeRateService.get(exchangeRate.getId()));
	}
	
	@Test
    public void testSearch() {
        // start create new data
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setDateCourse(new Date());
		exchangeRate.setConversion(1.11111111111111);
		Currency currency = currencyDao.get((long) 8);
		Currency currency1 = currencyDao.get((long) 8);
		exchangeRateService.add(exchangeRate, currency, currency1);

		ExchangeRateFilter filter = new ExchangeRateFilter();
        List<ExchangeRate> result = exchangeRateService.find(filter);
        // test paging
        filter.setFetchCredentials(true);
        filter.setDateCurrency(new Date());
        int limit = 3;
        filter.setLimit(limit);
        filter.setOffset(0);
        result = exchangeRateService.find(filter);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        // test sort
        filter.setLimit(null);
        filter.setOffset(null);
        filter.setSortOrder(true);
        filter.setSortProperty(ExchangeRate_.dateCourse);
        result = exchangeRateService.find(filter);
    }

}
