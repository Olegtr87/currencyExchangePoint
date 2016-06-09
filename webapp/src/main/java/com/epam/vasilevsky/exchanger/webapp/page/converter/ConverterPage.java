package com.epam.vasilevsky.exchanger.webapp.page.converter;

import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import com.epam.vasilevsky.exchanger.dataaccess.ExchangeRateDao;
import com.epam.vasilevsky.exchanger.dataaccess.OperationDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.service.CurrencyService;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.app.common.CurrencyChoiceRenderer;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.check.CheckPage;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;

public class ConverterPage extends AbstractHomePage {

	private Transaction transaction;

	private ExchangeRate exchangeRate;

	@Inject
	CurrencyService currencyService;

	@Inject
	OperationService operationService;

	@Inject
	ExchangeRateService exchangeRateService;

	private ExchangeRateFilter exchangeRateFilter;

	public ConverterPage(PageParameters parameters) {
		super(parameters);
	}

	public ConverterPage(Transaction transaction) {
		super();
		this.transaction = transaction;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Options options = new Options();
		options.set("button", true);

		final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback", options);
		this.add(feedback);

		exchangeRate = new ExchangeRate();

		Form form = new Form("form", new CompoundPropertyModel<Transaction>(transaction));
		add(form);

		DropDownChoice<Currency> currencyFromField = new DropDownChoice<Currency>("currencyFrom",
				new PropertyModel(exchangeRate, "currencyFrom"), currencyService.getAll(),
				CurrencyChoiceRenderer.INSTANCE);
		currencyFromField.setRequired(true);
		form.add(currencyFromField);

		DropDownChoice<Currency> currencyToField = new DropDownChoice<Currency>("currencyTo",
				new PropertyModel(exchangeRate, "currencyTo"), currencyService.getAll(),
				CurrencyChoiceRenderer.INSTANCE);
		currencyToField.setRequired(true);
		form.add(currencyToField);

		TextField<Integer> sumInField = new TextField<>("sumIn");
		sumInField.add(RangeValidator.<Integer> minimum(1));
		sumInField.setRequired(true);
		form.add(sumInField);

		form.add(new SubmitLink("transaction") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				if (searchExchangeRate() == null) {
					this.warn(getString("converter.nocourse"));
				} else if (exchangeRate.getCurrencyFrom().getName().equals(exchangeRate.getCurrencyTo().getName())) {
					this.warn(getString("converter.error.different"));
				}
				if (searchOperation().getStatusBlock() == true) {
					this.warn(getString("converter.error.block"));
				} else {
					setResponsePage(new CheckPage(transaction, exchangeRate));
				}
			}
		});
		add(feedback);
	}

	private Operation searchOperation() {
		if (exchangeRate.getCurrencyFrom().getName().equals(CurrencyName.BRB)
				&& (!exchangeRate.getCurrencyTo().getName().equals(CurrencyName.BRB))) {
			return operationService.get((long) 1);
		} else if (exchangeRate.getCurrencyTo().getName().equals(CurrencyName.BRB)
				&& (!exchangeRate.getCurrencyFrom().getName().equals(CurrencyName.BRB))) {
			return operationService.get((long) 2);
		} else {
			return operationService.get((long) 3);
		}
	}

	private ExchangeRate searchExchangeRate() {
		Date date = new Date();
		date.setHours(date.getHours() + 1);
		try {
			if (!exchangeRate.getCurrencyFrom().getName().equals(CurrencyName.BRB)
					&& !exchangeRate.getCurrencyTo().getName().equals("BRB")) {
				setFilterExRate(exchangeRate.getCurrencyFrom().getName(), CurrencyName.BRB);
				return exchangeRateService.find(exchangeRateFilter).get(0);
			}

			else if (!exchangeRate.getCurrencyFrom().getName().equals(CurrencyName.BRB)
					&& !exchangeRate.getCurrencyTo().getName().equals("BRB")) {
				setFilterExRate(CurrencyName.BRB, exchangeRate.getCurrencyTo().getName());
				return exchangeRateService.find(exchangeRateFilter).get(0);
			}

			else {
				setFilterExRate(exchangeRate.getCurrencyFrom().getName(), exchangeRate.getCurrencyTo().getName());
				return exchangeRateService.find(exchangeRateFilter).get(0);
			}

		} catch (IndexOutOfBoundsException e) {
			return null;
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
