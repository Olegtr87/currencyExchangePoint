package com.epam.vasilevsky.exchanger.webapp.page.course.panel;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.datetime.markup.html.basic.DateLabel;
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

import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate_;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;
import com.epam.vasilevsky.exchanger.webapp.page.course.CourseEditPage;
import com.epam.vasilevsky.exchanger.webapp.page.course.CoursePage;

public class CourseListPanel extends Panel {
	ExchangeRate exchangeRate;
	
	Date date;
	
	@Inject
	private ExchangeRateService exchangeRateService;
	
	private ExchangeRateFilter exchangeRateFilter;
	
	public CourseListPanel(String id) {
		super(id);
	}
	
	public CourseListPanel(String id, Date date) {
		super(id);
		this.date=date;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		ExchangeRateDataProvider exchangeRateDataProvider = new ExchangeRateDataProvider();
		DataView<ExchangeRate> dataView = new DataView<ExchangeRate>("rows", exchangeRateDataProvider, 5) {
			@Override
			protected void populateItem(Item<ExchangeRate> item) {
				exchangeRate = item.getModelObject();
				

				item.add(new Label("id", exchangeRate.getId()));
				item.add(new Label("conversion", viewCorrectCourse()));
				item.add(new Label("currency-from", exchangeRate.getCurrencyFrom().getName()));
				item.add(new Label("currency-to", exchangeRate.getCurrencyTo().getName()));
				item.add(DateLabel.forDatePattern("date", Model.of(exchangeRate.getDateCourse()), "dd-MM-yyyy"));

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new CourseEditPage(exchangeRate));
					}
				});

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							exchangeRateService.delete(exchangeRate.getId());
						} catch (PersistenceException e) {
							System.out.println("caughth persistance exception");
						}

						setResponsePage(new CoursePage(new ExchangeRate()));
					}
				});
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", ExchangeRate_.id, exchangeRateDataProvider));
		add(new OrderByBorder("sort-conversion", ExchangeRate_.conversion, exchangeRateDataProvider));
		add(new OrderByBorder("sort-date", ExchangeRate_.dateCourse, exchangeRateDataProvider));
		add(new OrderByBorder("sort-currency-from", ExchangeRate_.currencyFrom, exchangeRateDataProvider));
		add(new OrderByBorder("sort-currency-to", ExchangeRate_.currencyTo, exchangeRateDataProvider));
		
	}

	private class ExchangeRateDataProvider extends SortableDataProvider<ExchangeRate, Serializable> {


		public ExchangeRateDataProvider() {
			super();
			exchangeRateFilter = new ExchangeRateFilter();
			exchangeRateFilter.setFetchCredentials(true);
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
			if (date!=null) exchangeRateFilter.setDateCourse(date);
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
	
	private Double viewCorrectCourse(){
		if (exchangeRate.getCurrencyFrom().getName().name().equals(CurrencyName.BRB.name())) {
			return 1/exchangeRate.getConversion();} else return exchangeRate.getConversion();
	}
}
