package com.epam.vasilevsky.exchanger.webapp.page.balanceuser;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.service.BankUserAccountService;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.operations.panel.OperationsListPanel;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;

public class BalanceUserEditPage extends AbstractHomePage {

	BankAccountUser bankAccountUser;
	
	@Inject
	BankUserAccountService bankUserAccountService;
	
	private Integer newSum = 0;

	public Integer getNewSum() {
		return newSum;
	}

	public void setNewSum(Integer newSum) {
		this.newSum = newSum;
	}

	public BalanceUserEditPage(PageParameters parameters) {
		super(parameters);
	}

	public BalanceUserEditPage(BankAccountUser bankAccountUser) {
		this.bankAccountUser=bankAccountUser;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form form = new Form("form", new CompoundPropertyModel<BankAccountUser>(bankAccountUser));
		add(form);
		
		Options options = new Options();
		options.set("button", true);
		final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback", options);
		add(feedback);

		RequiredTextField<Integer> nameField = new RequiredTextField<>("balance");
		nameField.setEnabled(false);
		nameField.setRequired(true);
		form.add(nameField);
		
		form.add(new Label("currency"));
		
		TextField<Integer> newSumField = new TextField<>("newsum", new PropertyModel<Integer>(this, "newSum"));
		newSumField.add(RangeValidator.<Integer> range(0, Integer.MAX_VALUE));
		form.add(newSumField);
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				bankAccountUser.setBalance(bankAccountUser.getBalance()+newSum);
				bankUserAccountService.update(bankAccountUser);
				setResponsePage(new BalanceUserPage());
			}
		});
	}
}
