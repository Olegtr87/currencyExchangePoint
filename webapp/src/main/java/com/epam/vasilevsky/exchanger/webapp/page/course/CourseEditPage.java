package com.epam.vasilevsky.exchanger.webapp.page.course;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;


public class CourseEditPage extends AbstractHomePage {

	@Inject
	private ExchangeRateService exchangeRateService;

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

		TextField<String> currencyFrom = new TextField<>("currencyFrom");
		currencyFrom.setRequired(true);
		form.add(currencyFrom);
		
		TextField<String> currencyTo = new TextField<>("currencyTo");
		currencyTo.setRequired(true);
		form.add(currencyTo);

		TextField<Double> conversion = new TextField<>("conversion");
		conversion.add(RangeValidator.<Double> minimum(0.1d));
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
				exchangeRateService.saveOrUpdate(exchangeRate);
				setResponsePage(new CoursePage());
			}
		});

		add(new FeedbackPanel("feedback"));

	}

}
