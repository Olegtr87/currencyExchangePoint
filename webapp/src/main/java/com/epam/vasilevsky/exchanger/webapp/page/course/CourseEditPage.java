package com.epam.vasilevsky.exchanger.webapp.page.course;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.DateValidator;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.service.CurrencyService;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;
import com.epam.vasilevsky.exchanger.webapp.app.common.CurrencyChoiceRenderer;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;

public class CourseEditPage extends AbstractHomePage {

	@Inject
	private ExchangeRateService exchangeRateService;

	@Inject
	private CurrencyService currencyService;

	private ExchangeRate exchangeRate;

	public CourseEditPage(PageParameters parameters) {
		super(parameters);
	}

	public CourseEditPage(ExchangeRate exchangeRate) {
		super();
		this.exchangeRate = exchangeRate;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form form = new Form("form", new CompoundPropertyModel<ExchangeRate>(exchangeRate));
		add(form);

		DropDownChoice<Currency> currencyFromField = new DropDownChoice<Currency>("currencyFrom",
				currencyService.getAll(), CurrencyChoiceRenderer.INSTANCE);
		currencyFromField.setRequired(true);
		form.add(currencyFromField);

		DropDownChoice<Currency> currencyToField = new DropDownChoice<Currency>("currencyTo", currencyService.getAll(),
				CurrencyChoiceRenderer.INSTANCE);
		currencyToField.setRequired(true);
		form.add(currencyToField);

		TextField<Double> conversion = new TextField<>("conversion");
		conversion.add(RangeValidator.<Double> minimum(0.0d));
		conversion.setRequired(true);
		form.add(conversion);

		DateTextField dateCourseField = new DateTextField("dateCourse");
		dateCourseField.add(new DatePicker());
		dateCourseField.setRequired(true);
		form.add(dateCourseField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				try{
				if (exchangeRate.getCurrencyFrom().getName().equals(exchangeRate.getCurrencyTo().getName())) {
					error(getString("course.error.different"));
				} else if (checkDoubleBrb()) {
					error(getString("course.error.brb"));
				} else {
					setCorrectConversion();
					exchangeRateService.saveOrUpdate(exchangeRate);
					setResponsePage(new CoursePage(new ExchangeRate()));
				}
				}catch(PersistenceException e){
					error(getString("course.error.duplicate"));
				}
			}
		});

		add(new FeedbackPanel("feedback"));
	}

	private void setCorrectConversion() {
		if (exchangeRate.getCurrencyFrom().getName().name().equals(CurrencyName.BRB.name())) {
			exchangeRate.setConversion(1 / exchangeRate.getConversion());
		}
	}
	
	private boolean checkDoubleBrb(){
		return ((!exchangeRate.getCurrencyFrom().getName().name().equals(CurrencyName.BRB.name()))
				&& (!exchangeRate.getCurrencyTo().getName().name().equals(CurrencyName.BRB.name())));
	}
}
