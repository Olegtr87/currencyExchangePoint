package com.epam.vasilevsky.exchanger.webapp.page.check;

import java.util.Date;

import javax.inject.Inject;

import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.service.CurrencyService;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.service.TransactionService;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;

public class CheckPage extends AbstractHomePage {

	private Transaction transaction;
	private ExchangeRate exchangeRate;

	@Inject
	OperationService operationService;
	@Inject
	CurrencyService currencyService;
	@Inject
	ExchangeRateService exchangeRateService;
	@Inject
	TransactionService transactionService;

	public CheckPage(Transaction transaction, ExchangeRate exchangeRate) {
		super();
		this.transaction = transaction;
		this.exchangeRate = exchangeRate;
	}

	private ExchangeRateFilter exchangeRateFilter;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setTransaction();
		transactionService.add(transaction, AuthorizedSession.get().getLoggedUser(), searchOperation(), searchExchangeRate());
	}

	private void setTransaction() {
		transaction.setOperation(searchOperation());
		transaction.setDateOperation(new Date());
		transaction.setExchangeRate(searchExchangeRate());
		transaction.setUser(AuthorizedSession.get().getLoggedUser());
	}

	private Operation searchOperation() {
		if (exchangeRate.getCurrencyFrom().getName().equals(CurrencyName.BRB))
			return operationService.get((long) 1);
		else if (exchangeRate.getCurrencyTo().getName().equals(CurrencyName.BRB))
			return operationService.get((long) 2);
		else
			return operationService.get((long) 3);
	}

	private ExchangeRate searchExchangeRate() {
		exchangeRateFilter = new ExchangeRateFilter();
		exchangeRateFilter.setCurrencyFrom(exchangeRate.getCurrencyFrom().getName());
		exchangeRateFilter.setCurrencyTo(exchangeRate.getCurrencyTo().getName());
		exchangeRateFilter.setDateCourse(new Date());
		exchangeRateFilter.setFetchCredentials(true);
		return exchangeRateService.find(exchangeRateFilter).get(0);
	}

}
