package com.epam.vasilevsky.exchanger.webapp.page.balancebank;

import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.balancebank.panel.BalanceListPanel;
import com.googlecode.wicket.jquery.ui.markup.html.link.Link;

public class BalancePage extends AbstractHomePage {

	private String name;

	public BalancePage(String name) {
		this.name = name;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form form = new Form("form");
		add(form);

		add(new BalanceListPanel("list-panel",name));

		RequiredTextField<String> nameField = new RequiredTextField<>("name",new Model<String>());
		form.add(nameField);
		
		form.add(new SubmitLink("filter") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new BalancePage(nameField.getModelObject()));
			}
		});
	}
}
