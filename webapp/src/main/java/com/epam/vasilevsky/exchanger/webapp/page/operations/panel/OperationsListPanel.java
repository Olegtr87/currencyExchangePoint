package com.epam.vasilevsky.exchanger.webapp.page.operations.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Operation_;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.webapp.page.operations.OperationEditPage;
import com.epam.vasilevsky.exchanger.webapp.page.operations.OperationPage;

public class OperationsListPanel extends Panel {

	@Inject
	private OperationService operationService;

	public OperationsListPanel(String id) {
		super(id);

		OperationsDataProvider operationsDataProvider = new OperationsDataProvider();
		DataView<Operation> dataView = new DataView<Operation>("rows", operationsDataProvider, 5) {
			@Override
			protected void populateItem(Item<Operation> item) {
				Operation operation = item.getModelObject();

				item.add(new Label("id", operation.getId()));
				item.add(new Label("name", operation.getName()));
				item.add(new Label("tax", operation.getTax()));

				CheckBox checkbox = new CheckBox("status-block", Model.of(operation.getStatusBlock()));
				checkbox.setEnabled(false);
				item.add(checkbox);

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new OperationEditPage(operation));
					}
				});
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", Operation_.id, operationsDataProvider));
		add(new OrderByBorder("sort-name", Operation_.name, operationsDataProvider));
		add(new OrderByBorder("sort-tax", Operation_.tax, operationsDataProvider));
	}

	private class OperationsDataProvider extends SortableDataProvider<Operation, Serializable> {

		private OperationFilter operationFilter;

		public OperationsDataProvider() {
			super();
			operationFilter = new OperationFilter();
			setSort((Serializable) Operation_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Operation> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			operationFilter.setSortProperty((SingularAttribute) property);
			operationFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			operationFilter.setLimit((int) count);
			operationFilter.setOffset((int) first);
			return operationService.find(operationFilter).iterator();
		}

		@Override
		public long size() {
			return operationService.count(operationFilter);
		}

		@Override
		public IModel<Operation> model(Operation object) {
			return new Model(object);
		}

	}
}
