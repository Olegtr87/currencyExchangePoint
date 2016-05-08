package com.epam.vasilevsky.exchanger.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CodeCurrency;
import com.epam.vasilevsky.exchanger.service.coursenbrb.CourseNBRB;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class CourseNBRBTest {

	@Test
	public void test() {
		CourseNBRB course = new CourseNBRB(CodeCurrency.getCurrencyFromName(CurrencyName.EUR));
		String a = course.getCourse();
		System.out.println(a);
	}
}
