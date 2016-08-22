package com.epam.vasilevsky.exchanger.webapp.page.operations;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.operations.panel.OperationsListPanel;

public class OperationEditPage extends AbstractHomePage {

	@Inject
	private OperationService operationService;

	private Operation operation;

	public OperationEditPage(PageParameters parameters) {
		super(parameters);
	}

	public OperationEditPage(Operation operation) {
		super();
		this.operation = operation;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form form = new Form("form", new CompoundPropertyModel<Operation>(operation));
		add(form);

		TextField<String> nameField = new TextField<>("name");
		nameField.setEnabled(false);
		nameField.setRequired(true);
		form.add(nameField);
		
		TextField<Double> tax = new TextField<>("tax");
		tax.add(RangeValidator.<Double> range(0d, 100d));
		tax.setLabel(new ResourceModel("operations.label.tax"));
		tax.setRequired(true);
		form.add(tax);

		CheckBox activeField = new CheckBox("statusBlock");
		form.add(activeField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				operationService.saveOrUpdate(operation);
				setResponsePage(new OperationPage());
			}
		});

		add(new FeedbackPanel("feedback"));

	}

}
