package com.epam.vasilevsky.exchanger.webapp.page.converter;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.webapp.app.common.CurrencyChoiceRenderer;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.check.CheckPage;
import com.epam.vasilevsky.exchanger.webapp.page.course.CoursePage;

public class ConverterPage extends AbstractHomePage {
	@Inject
	private CurrencyDao currencyDao;
	
	private ExchangeRate exchangeRate;
	
	public ConverterPage(ExchangeRate exchangeRate) {
		super();
		this.exchangeRate = exchangeRate;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form form = new Form("form", new CompoundPropertyModel<ExchangeRate>(exchangeRate));
		add(form);

		DropDownChoice<Currency> currencyFromField = new DropDownChoice<Currency>("currencyFrom", currencyDao.getAll(),CurrencyChoiceRenderer.INSTANCE);
        currencyFromField.setRequired(true);
        form.add(currencyFromField);

        DropDownChoice<Currency> currencyToField = new DropDownChoice<Currency>("currencyTo",currencyDao.getAll(),CurrencyChoiceRenderer.INSTANCE);
        currencyToField.setRequired(true);
        form.add(currencyToField);
		

		TextField<Integer> conversion = new TextField<>("sumIn",new PropertyModel(new Transaction(), "sumIn"));
		//conversion.add(RangeValidator.<Integer> minimum(0));
		conversion.setRequired(true);
		form.add(conversion);

		form.add(new SubmitLink("transaction") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				//exchangeRateService.saveOrUpdate(exchangeRate);
				setResponsePage(new CheckPage());
			}
		});

		add(new FeedbackPanel("feedback"));

	}

}
