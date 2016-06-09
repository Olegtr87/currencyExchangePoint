package com.epam.vasilevsky.exchanger.webapp.page.balancebank;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.operations.panel.OperationsListPanel;

public class BalanceEditPage extends AbstractHomePage {

	@Inject
	private OperationService operationService;

	private Balance currency;

	public BalanceEditPage(PageParameters parameters) {
		super(parameters);
	}

	public BalanceEditPage(Balance currency) {
		super();
		this.currency = currency;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form form = new Form("form", new CompoundPropertyModel<Operation>(operation));
		add(form);

		TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);
		
		TextField<Double> basePriceField = new TextField<>("tax");
		basePriceField.add(RangeValidator.<Double> range(0d, 100d));
		basePriceField.setRequired(true);
		form.add(basePriceField);

		CheckBox activeField = new CheckBox("statusBlock");
		form.add(activeField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				operationService.saveOrUpdate(operation);
				setResponsePage(new BalancePage());
			}
		});

		add(new FeedbackPanel("feedback"));

	}

}
