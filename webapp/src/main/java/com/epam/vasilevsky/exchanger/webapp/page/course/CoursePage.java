package com.epam.vasilevsky.exchanger.webapp.page.course;

import java.util.Date;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.DateValidator;

import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.course.panel.CourseListPanel;

public class CoursePage extends AbstractHomePage {
	
	public CoursePage(ExchangeRate exchangeRate){
		this.exchangeRate=exchangeRate;
	}
	
	ExchangeRate exchangeRate;

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new Link("create") {
			@Override
			public void onClick() {
				setResponsePage(new CourseEditPage(new ExchangeRate()));
			}
		});

		Form form = new Form("form", new CompoundPropertyModel<ExchangeRate>(exchangeRate));
		add(form);
		
		add(new CourseListPanel("list-panel",exchangeRate.getDateCourse()));
		
		DateTextField dateIssueField=new DateTextField("dateCourse");
		dateIssueField.add(new DatePicker());
		dateIssueField.setRequired(true);
		dateIssueField.add(DateValidator.maximum(new Date()));
		form.add(dateIssueField);

		form.add(new SubmitLink("filter") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new CoursePage(exchangeRate));
			}
		});
	}
}
