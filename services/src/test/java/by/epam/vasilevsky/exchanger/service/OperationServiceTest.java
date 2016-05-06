package by.epam.vasilevsky.exchanger.service;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.epam.vasilevsky.exchanger.dataaccess.OperationDao;
import by.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;
import by.epam.vasilevsky.exchanger.dataaccess.impl.AbstractDaoImpl;
import by.epam.vasilevsky.exchanger.datamodel.Operation;
import by.epam.vasilevsky.exchanger.datamodel.Operation_;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class OperationServiceTest {

	@Inject
	private OperationService operationService;

	@Inject
	private OperationDao operationDao;
	
	@Test
	public void test() {
		Assert.assertNotNull(operationService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(operationDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void testAddOperation() {
		Operation operation=new Operation();
		operation.setName(System.currentTimeMillis() + "convert");
		operation.setStatusBlock(false);
		operation.setTax(1.5);
		operationService.add(operation);
		
		Assert.assertNotNull(operationDao.get(operation.getId()).getId());
	}
	
	@Test
	public void testUpdateOperation() {
		Operation operation=new Operation();
		operation.setName(System.currentTimeMillis() + "convert");
		operation.setStatusBlock(false);
		operation.setTax(1.7);
		operationService.add(operation);
		Double upd=2.0;
		operation.setTax(upd);
		operationService.update(operation);
		
		Assert.assertEquals(upd,operationService.get(operation.getId()).getTax());
	}
	
	@Test
	public void testDeleteOperation() {
		Operation operation=new Operation();
		operation.setName(System.currentTimeMillis() + "convert");
		operation.setStatusBlock(false);
		operation.setTax(1.7);
		operationService.add(operation);
		
		operationService.delete(operation.getId());
		
		Assert.assertNull(operationService.get(operation.getId()));
	}
	
	@Test
    public void testSearch() {
        // start create new data
		Operation operation=new Operation();
		operation.setName(System.currentTimeMillis() + "convert");
		operation.setStatusBlock(false);
		operation.setTax(1.7);
		operationService.add(operation);

		OperationFilter filter = new OperationFilter();
        List<Operation> result = operationService.find(filter);
        // test paging
        filter.setFetchCredentials(true);
        filter.setOperationName("sell");
        int limit = 3;
        filter.setLimit(limit);
        filter.setOffset(0);
        result = operationService.find(filter);
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        // test sort
        filter.setLimit(null);
        filter.setOffset(null);
        filter.setSortOrder(true);
        filter.setSortProperty(Operation_.name);
        result = operationService.find(filter);
        for (Operation oper: result){
        	System.out.println(oper);
        }
    }
}
