package com.epam.vasilevsky.exchanger.webapp.page.check;

import java.util.Date;
import javax.inject.Inject;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.DownloadLink;
import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.service.TransactionService;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;

public class CheckPage extends AbstractHomePage {

	private Transaction transaction;
	private ExchangeRate exchangeRate;

	@Inject
	OperationService operationService;
	@Inject
	ExchangeRateService exchangeRateService;
	@Inject
	TransactionService transactionService;
	@Inject
	UserService userService;

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
		transactionService.add(transaction, AuthorizedSession.get().getLoggedUser(), searchOperation(),
				searchExchangeRate());

		add(new Label("message", "The transaction has been successful"));
		add(new Label("number_check", "Number check " + transaction.getId()));
		add(new Label("payer", "Payer: " + userService.getProfile(transaction.getUser().getId()).getFirstName() + " "
				+ userService.getProfile(transaction.getUser().getId()).getLastName()));
		add(new Label("operation", "Operation: " + transaction.getOperation().getName()));
		add(new Label("sumin", "Sum in: " + transaction.getSumIn()));
		add(new Label("currency", "Currency from " + transaction.getExchangeRate().getCurrencyFrom().getName() + " to "
				+ transaction.getExchangeRate().getCurrencyTo().getName()));
		add(new Label("total", "Total: " + totalSum()));

		CheckPdf checkPdf = new CheckPdf(transaction, userService);
		add(new DownloadLink("download", checkPdf.createPdf(), "download.pdf"));

	}

	private Integer totalSum() {
		Double totalNoCom = transaction.getSumIn() * transaction.getExchangeRate().getConversion();
		Double tax = transaction.getExchangeRate().getConversion() * transaction.getSumIn()
				* transaction.getOperation().getTax() / 100;
		Double total = totalNoCom - tax;
		return (int) Math.round(total);
	}

	private void setTransaction() {
		transaction.setOperation(searchOperation());
		transaction.setDateOperation(new Date());
		transaction.setExchangeRate(searchExchangeRate());
		transaction.setUser(AuthorizedSession.get().getLoggedUser());
	}

	private Operation searchOperation() {
		if (getCuurencyFromName().equals(CurrencyName.BRB) && (!getCuurencyToName().equals(CurrencyName.BRB))) {
			return operationService.get((long) 1);
		} else if (getCuurencyToName().equals(CurrencyName.BRB) && (!getCuurencyFromName().equals(CurrencyName.BRB))) {
			return operationService.get((long) 2);
		} else {
			return operationService.get((long) 3);
		}
	}

	private CurrencyName getCuurencyFromName() {
		return exchangeRate.getCurrencyFrom().getName();
	}

	private CurrencyName getCuurencyToName() {
		return exchangeRate.getCurrencyTo().getName();
	}

	private ExchangeRate searchExchangeRate() {
		Date date = new Date();
		date.setHours(date.getHours() + 1);
		exchangeRateFilter = new ExchangeRateFilter();
		exchangeRateFilter.setCurrencyFrom(exchangeRate.getCurrencyFrom().getName());
		exchangeRateFilter.setCurrencyTo(exchangeRate.getCurrencyTo().getName());
		exchangeRateFilter.setDateCourse(date);
		exchangeRateFilter.setFetchCredentials(true);
		return exchangeRateService.find(exchangeRateFilter).get(0);
	}
}
