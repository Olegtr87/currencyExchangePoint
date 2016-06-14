package com.epam.vasilevsky.exchanger.webapp.page.invoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.DownloadLink;

import com.epam.vasilevsky.exchanger.dataaccess.filters.BalanceFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.BankAccountUserFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.service.BalanceService;
import com.epam.vasilevsky.exchanger.service.BankUserAccountService;
import com.epam.vasilevsky.exchanger.service.CurrencyService;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.service.TransactionService;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;

public class InvoicePage extends AbstractHomePage {
	final static private String CONVERTATION = "convertation";
	private Transaction transaction;
	private ExchangeRate exchangeRate;
	private Balance balance;

	private List<ExchangeRate> list;

	@Inject
	OperationService operationService;
	@Inject
	ExchangeRateService exchangeRateService;
	@Inject
	TransactionService transactionService;
	@Inject
	UserService userService;
	@Inject
	BalanceService balanceService;
	@Inject
	CurrencyService currencyService;
	@Inject
	BankUserAccountService bankUserAccountService;

	private ExchangeRateFilter exchangeRateFilter;

	public InvoicePage(Transaction transaction, ExchangeRate exchangeRate) {
		super();
		this.transaction = transaction;
		this.exchangeRate = exchangeRate;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (searchOperation().getName().equals("convertation")) {
			pdfForConvertation();
		} else {
			pdfForSellOrBuy();
		}
	}

	private void pdfForConvertation() {
		setTransaction();
		transactionService.add(transaction, AuthorizedSession.get().getLoggedUser(), operationService.get((long) 2),
				searchExchangeRate().get(0));
		Integer totalSum = transaction.getTotalSum();
		Integer sumInSum = transaction.getSumIn();
		transaction = new Transaction();
		transaction.setDateOperation(new Date());
		transaction.setExchangeRate(list.get(1));
		transaction.setOperation(operationService.get((long) 1));
		transaction.getOperation().setTax(operationService.get((long) 3).getTax());
		transaction.setSumIn(totalSum);
		transaction.setTotalSum(totalSum());
		transaction.setUser(AuthorizedSession.get().getLoggedUser());
		transactionService.add(transaction, AuthorizedSession.get().getLoggedUser(), operationService.get((long) 1),
				list.get(1));

		add(new Label("message", getString("check.label.successful")+":"));
		add(new Label("number_check",
				getString("check.label.numbercheck") + ": " + (transaction.getId() - 1) + " " + transaction.getId()));
		add(new Label("payer",
				getString("check.label.payer") + ": "
						+ userService.getProfile(transaction.getUser().getId()).getFirstName() + " "
						+ userService.getProfile(transaction.getUser().getId()).getLastName()));
		add(new Label("operation", getString("operations.label.operation") + ": " + CONVERTATION));
		add(new Label("sumin", getString("converter.sumin") + ": " + sumInSum));
		add(new Label("currency", getString("editcourse.label.currency.from") + " "
				+ searchExchangeRate().get(0).getCurrencyFrom().getName() + " " + getString("editcourse.label.to") + " "
				+ searchExchangeRate().get(1).getCurrencyTo().getName()));
		add(new Label("total", getString("transacions.label.total") + ": " + totalSum() + " "
				+ searchExchangeRate().get(1).getCurrencyTo().getName()));

		InvoicePdf checkPdf = new InvoicePdf(transaction, userService, CONVERTATION, sumInSum,
				searchExchangeRate().get(0).getCurrencyFrom().getName().name());
		add(new DownloadLink("download", checkPdf.createPdf(), "download.pdf"));
	}

	private void pdfForSellOrBuy() {
		setTransaction();
		transactionService.add(transaction, AuthorizedSession.get().getLoggedUser(), searchOperation(),
				searchExchangeRate().get(0));
		

		add(new Label("message", getString("check.label.successful")+":"));
		add(new Label("number_check", getString("check.label.numbercheck") + ": " + transaction.getId()));
		add(new Label("payer",
				getString("check.label.payer") + ": "
						+ userService.getProfile(transaction.getUser().getId()).getFirstName() + " "
						+ userService.getProfile(transaction.getUser().getId()).getLastName()));
		add(new Label("operation",
				getString("operations.label.operation") + ": " + transaction.getOperation().getName()));
		add(new Label("sumin", getString("converter.sumin") + ": " + transaction.getSumIn()));
		add(new Label("currency", getString("editcourse.label.currency.from") + " "
				+ transaction.getExchangeRate().getCurrencyFrom().getName()));
		add(new Label("total", getString("transacions.label.total") + ": " + totalSum()+" "+ transaction.getExchangeRate().getCurrencyTo().getName()));
		
		lowBalanceBank();
		lowBalanceUser();
		
		InvoicePdf checkPdf = new InvoicePdf(transaction, userService);
		add(new DownloadLink("download", checkPdf.createPdf(), "download.pdf"));
	}

	private void lowBalanceBank() {
		BalanceFilter filter = new BalanceFilter();
		filter.setFetchCredentials(true);
		filter.setCurrencyName(exchangeRate.getCurrencyTo().getName());
		balance = balanceService.find(filter).get(0);
		balance.setCurrency(exchangeRate.getCurrencyTo());
		balance.setSum(balanceService.find(filter).get(0).getSum() - totalSum());
		currencyService.updateBalance(balance);
	}
	
	private void lowBalanceUser() {
		BankAccountUser userBalance=new BankAccountUser();
		BankAccountUserFilter filter=new BankAccountUserFilter();
		filter.setCurrency(exchangeRate.getCurrencyFrom().getName());
		filter.setUserCredentials(AuthorizedSession.get().getLoggedUser());
		userBalance=bankUserAccountService.findBankUserAccount(filter).get(0);
		userBalance.setBalance(userBalance.getBalance()-transaction.getSumIn());
		userBalance.setCurrency(exchangeRate.getCurrencyFrom().getName());
		userBalance.setUser(AuthorizedSession.get().getLoggedUser());
		bankUserAccountService.update(userBalance);
		
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
		transaction.setExchangeRate(searchExchangeRate().get(0));
		transaction.setUser(AuthorizedSession.get().getLoggedUser());
		transaction.setTotalSum(totalSum());
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

	private List<ExchangeRate> searchExchangeRate() {
		if (exchangeRate.getCurrencyFrom().getName().equals(CurrencyName.BRB)
				|| exchangeRate.getCurrencyTo().getName().equals(CurrencyName.BRB)) {
			setFilterExRate(getCuurencyFromName(), getCuurencyToName());
			return exchangeRateService.find(exchangeRateFilter);
		} else {
			list = new ArrayList<ExchangeRate>();
			setFilterExRate(getCuurencyFromName(), CurrencyName.BRB);
			list.add(exchangeRateService.find(exchangeRateFilter).get(0));
			setFilterExRate(CurrencyName.BRB, getCuurencyToName());
			list.add(exchangeRateService.find(exchangeRateFilter).get(0));
			return list;
		}
	}

	private void setFilterExRate(CurrencyName from, CurrencyName to) {
		Date date = new Date();
		date.setHours(date.getHours() + 1);
		exchangeRateFilter = new ExchangeRateFilter();
		exchangeRateFilter.setCurrencyFrom(from);
		exchangeRateFilter.setCurrencyTo(to);
		exchangeRateFilter.setDateCourse(date);
		exchangeRateFilter.setFetchCredentials(true);
	}
}
