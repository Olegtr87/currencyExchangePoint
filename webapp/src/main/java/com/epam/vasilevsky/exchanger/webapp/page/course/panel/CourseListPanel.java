package com.epam.vasilevsky.exchanger.webapp.page.course.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate_;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;


public class CourseListPanel extends Panel {

	@Inject
	private ExchangeRateService exchangeRateService;

	public CourseListPanel(String id) {
		super(id);

		ExchangeRateDataProvider exchangeRateDataProvider = new ExchangeRateDataProvider();
		DataView<ExchangeRate> dataView = new DataView<ExchangeRate>("rows", exchangeRateDataProvider, 5) {
			@Override
			protected void populateItem(Item<ExchangeRate> item) {
				ExchangeRate exchangeRate = item.getModelObject();

				item.add(new Label("id", exchangeRate.getId()));
				item.add(new Label("currency-from", exchangeRate.getCurrencyIdFrom().getName()));
				item.add(new Label("currency-to", exchangeRate.getCurrencyIdTo().getName()));
				item.add(DateLabel.forDatePattern("date", Model.of(exchangeRate.getDateCourse()), "dd-MM-yyyy"));
				
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", ExchangeRate_.id, exchangeRateDataProvider));
		add(new OrderByBorder("sort-date", ExchangeRate_.dateCourse, exchangeRateDataProvider));
		add(new OrderByBorder("sort-currency-from", ExchangeRate_.currencyFrom, exchangeRateDataProvider));
		add(new OrderByBorder("sort-currency-to", ExchangeRate_.currencyTo, exchangeRateDataProvider));
		
	}

	private class ExchangeRateDataProvider extends SortableDataProvider<ExchangeRate, Serializable> {

		private ExchangeRateFilter exchangeRateFilter;

		public ExchangeRateDataProvider() {
			super();
			exchangeRateFilter = new ExchangeRateFilter();
			setSort((Serializable) ExchangeRate_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<ExchangeRate> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			exchangeRateFilter.setSortProperty((SingularAttribute) property);
			exchangeRateFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			exchangeRateFilter.setLimit((int) count);
			exchangeRateFilter.setOffset((int) first);
			return exchangeRateService.find(exchangeRateFilter).iterator();
		}

		@Override
		public long size() {
			return exchangeRateService.count(exchangeRateFilter);
		}

		@Override
		public IModel<ExchangeRate> model(ExchangeRate object) {
			return new Model(object);
		}

	}
}
