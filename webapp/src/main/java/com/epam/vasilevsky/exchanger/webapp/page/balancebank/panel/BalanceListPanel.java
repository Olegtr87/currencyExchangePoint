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

import com.epam.vasilevsky.exchanger.dataaccess.filters.BalanceFilter;
import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.Balance_;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.service.BalanceService;
import com.epam.vasilevsky.exchanger.webapp.page.balancebank.BalanceEditPage;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;

public class BalanceListPanel extends Panel {

	private Balance balance;
	
	public KendoFeedbackPanel feedback;

	@Inject
	private BalanceService balanceService;

	String name;

	public BalanceListPanel(String id) {
		super(id);
	}

	public BalanceListPanel(String id, String name) {
		super(id);
		System.out.println(name);
		this.name = name;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Options options = new Options();
		options.set("button", true);

		feedback = new KendoFeedbackPanel("feedback", options);
		this.add(feedback);

		BalanceDataProvider balanceDataProvider = new BalanceDataProvider();
		DataView<Balance> dataView = new DataView<Balance>("rows", balanceDataProvider, 5) {
			@Override
			protected void populateItem(Item<Balance> item) {
				balance = item.getModelObject();
				item.add(new Label("id", balance.getId()));
				item.add(new Label("sum", balance.getSum()));
				item.add(new Label("currency", balance.getCurrency().getName()));

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
			try {
				if (!name.equals(""))
					balanceFilter.setCurrencyName(CurrencyName.valueOf(name));
			} catch (IllegalArgumentException e) {
				feedback.info(getString("balance.panel.error"));
			}

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
