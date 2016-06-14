package com.epam.vasilevsky.exchanger.webapp.page.balanceuser.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
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

import com.epam.vasilevsky.exchanger.dataaccess.filters.BankAccountUserFilter;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser_;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.service.BankUserAccountService;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.page.balanceuser.BalanceUserEditPage;
import com.epam.vasilevsky.exchanger.webapp.page.operations.OperationEditPage;

public class BalanceUserListPanel extends Panel {

	@Inject
	UserService userService;

	@Inject
	BankUserAccountService bankUserAccountService;

	public BalanceUserListPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		BankAccountUserDataProvider bankAccountUserDataProvider = new BankAccountUserDataProvider();

		DataView<BankAccountUser> dataView = new DataView<BankAccountUser>("rows", bankAccountUserDataProvider, 5) {
			@Override
			protected void populateItem(Item<BankAccountUser> item) {
				BankAccountUser bankAccountUser = item.getModelObject();

				item.add(new Label("balance", bankAccountUser.getBalance()));
				item.add(new Label("currency", bankAccountUser.getCurrency().name()));

				item.add(new Link<Void>("add-link") {
					@Override
					public void onClick() {
						setResponsePage(new BalanceUserEditPage(bankAccountUser));
					}
				});
			}
		};

		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-balance", BankAccountUser_.balance, bankAccountUserDataProvider));
		add(new OrderByBorder("sort-currency", BankAccountUser_.currency, bankAccountUserDataProvider));
	}

	private class BankAccountUserDataProvider extends SortableDataProvider<BankAccountUser, Serializable> {
		private BankAccountUserFilter userAccountFilter;

		public BankAccountUserDataProvider() {
			super();
			userAccountFilter = new BankAccountUserFilter();
			userAccountFilter.setFetchCredentials(true);
			UserCredentials user = AuthorizedSession.get().getLoggedUser();
			userAccountFilter.setUserCredentials(userService.getCredentials(user.getId()));
			setSort((Serializable) BankAccountUser_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<BankAccountUser> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			userAccountFilter.setSortProperty((SingularAttribute) property);
			userAccountFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);
			userAccountFilter.setLimit((int) count);
			userAccountFilter.setOffset((int) first);

			return bankUserAccountService.findBankUserAccount(userAccountFilter).iterator();
		}

		@Override
		public long size() {
			return bankUserAccountService.count(userAccountFilter);
		}

		@Override
		public IModel<BankAccountUser> model(BankAccountUser object) {
			return new Model(object);
		}
	}

}
