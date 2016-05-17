package com.epam.vasilevsky.exchanger.webapp.page.transaction;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.datamodel.Transaction_;
import com.epam.vasilevsky.exchanger.service.TransactionService;

public class TransactionsListPanel extends Panel {

	@Inject
	private TransactionService transactionService;

	public TransactionsListPanel(String id) {
		super(id);

		TransactionsDataProvider transactionsDataProvider = new TransactionsDataProvider();
		DataView<Transaction> dataView = new DataView<Transaction>("rows", transactionsDataProvider, 5) {
			@Override
			protected void populateItem(Item<Transaction> item) {
				Transaction transaction = item.getModelObject();

				item.add(new Label("id", transaction.getId()));
				item.add(DateLabel.forDatePattern("date", Model.of(transaction.getDateOperation()), "dd-MM-yyyy hh:mm"));
				item.add(new Label("sumin", transaction.getSummIn()));
				item.add(new Label("operation", transaction.getOperationId().getName()));
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", Transaction_.id, transactionsDataProvider));
		add(new OrderByBorder("sort-date", Transaction_.dateOperation, transactionsDataProvider));
		add(new OrderByBorder("sort-sum-in", Transaction_.sumIn, transactionsDataProvider));

	}

	private class TransactionsDataProvider extends SortableDataProvider<Transaction, Serializable> {

		private TransactionFilter transactionFilter;

		public TransactionsDataProvider() {
			super();
			transactionFilter = new TransactionFilter();
			setSort((Serializable) Transaction_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Transaction> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			transactionFilter.setSortProperty((SingularAttribute) property);
			transactionFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			transactionFilter.setLimit((int) count);
			transactionFilter.setOffset((int) first);
			return transactionService.find(transactionFilter).iterator();
		}

		@Override
		public long size() {
			return transactionService.count(transactionFilter);
		}

		@Override
		public IModel<Transaction> model(Transaction object) {
			return new Model(object);
		}

	}
}
