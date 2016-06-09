package com.epam.vasilevsky.exchanger.webapp.page.balancebank.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.epam.vasilevsky.exchanger.dataaccess.BalanceDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.BalanceFilter;
import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.Balance_;
import com.epam.vasilevsky.exchanger.service.BalanceService;
import com.epam.vasilevsky.exchanger.service.CurrencyService;
import com.epam.vasilevsky.exchanger.webapp.page.balancebank.BalanceEditPage;

public class BalanceListPanel extends Panel {

	@Inject
	private BalanceService balanceService;
	
	public BalanceListPanel(String id) {
		super(id);

		BalanceDataProvider balanceDataProvider = new BalanceDataProvider();
		DataView<Balance> dataView = new DataView<Balance>("rows",balanceDataProvider, 5) {
			@Override
			protected void populateItem(Item<Balance> item) {
				Balance balance = item.getModelObject();

				item.add(new Label("id", balance.getId()));
				item.add(new Label("sum", balance.getSum()));
				item.add(new Label("currency",balance.getCurrency().getName()));

				item.add(new Link<Void>("add-link") {
					@Override
					public void onClick() {
						setResponsePage(new BalanceEditPage(balance));
					}
				});

			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));
	}

	private class BalanceDataProvider extends SortableDataProvider<Balance, Serializable> {

		private BalanceFilter balanceFilter;

		public BalanceDataProvider() {
			super();
			balanceFilter = new BalanceFilter();
			balanceFilter.setFetchCredentials(true);
			setSort((Serializable) Balance_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Balance> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			balanceFilter.setSortProperty((SingularAttribute) property);
			balanceFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			balanceFilter.setLimit((int) count);
			balanceFilter.setOffset((int) first);
			return balanceService.find(balanceFilter).iterator();
		}

		@Override
		public long size() {
			return balanceService.count(balanceFilter);
		}

		@Override
		public IModel<Balance> model(Balance object) {
			return new Model(object);
		}

	}
}
