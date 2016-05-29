package com.epam.vasilevsky.exchanger.webapp.page.converter;

import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

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

public class ConverterPage extends AbstractHomePage {

	private Transaction transaction;

	private ExchangeRate exchangeRate;
	
	@Inject
	CurrencyService currencyService;
	
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

		exchangeRate = new ExchangeRate();

		Form form = new Form("form", new CompoundPropertyModel<Transaction>(transaction));
		add(form);

		DropDownChoice<Currency> currencyFromField = new DropDownChoice<Currency>("currencyFrom",
				new PropertyModel(exchangeRate, "currencyFrom"), currencyService.getAll(), CurrencyChoiceRenderer.INSTANCE);
		currencyFromField.setRequired(true);
		form.add(currencyFromField);

		DropDownChoice<Currency> currencyToField = new DropDownChoice<Currency>("currencyTo",
				new PropertyModel(exchangeRate, "currencyTo"), currencyService.getAll(), CurrencyChoiceRenderer.INSTANCE);
		currencyToField.setRequired(true);
		form.add(currencyToField);

		TextField<String> sumInField = new TextField<>("sumIn");
		sumInField.setRequired(true);
		form.add(sumInField);

		form.add(new SubmitLink("transaction") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new CheckPage(transaction,exchangeRate));
			}
		});

		add(new FeedbackPanel("feedback"));
	}
}
