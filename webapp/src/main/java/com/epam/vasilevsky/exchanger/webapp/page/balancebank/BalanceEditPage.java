package com.epam.vasilevsky.exchanger.webapp.page.balancebank;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.service.CurrencyService;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;

public class BalanceEditPage extends AbstractHomePage {

	@Inject
	private CurrencyService currencyService;

	private Balance balance;

	public BalanceEditPage(PageParameters parameters) {
		super(parameters);
	}

	public BalanceEditPage(Balance balance) {
		super();
		this.balance = balance;
	}

	private Integer newSum = 0;

	public Integer getNewSum() {
		return newSum;
	}

	public void setNewSum(Integer newSum) {
		this.newSum = newSum;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form form = new Form("form", new CompoundPropertyModel<Balance>(balance));
		add(form);

		Options options = new Options();
		options.set("button", true);
		final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback", options);
		add(feedback);

		TextField<Integer> sumField = new TextField<>("sum");
		sumField.setEnabled(false);
		form.add(sumField);

		TextField<Integer> newSumField = new TextField<>("newsum", new PropertyModel<Integer>(this, "newSum"));
		newSumField.setLabel(new ResourceModel("balance.edit.newsum"));
		newSumField.add(RangeValidator.<Integer> range(0, Integer.MAX_VALUE));
		form.add(newSumField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				balance.setSum(balance.getSum() + newSum);
				currencyService.updateBalance(balance);
				setResponsePage(new BalancePage(new String()));
			}
		});
	}
}
