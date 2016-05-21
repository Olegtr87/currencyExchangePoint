package com.epam.vasilevsky.exchanger.dataaccess.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.epam.vasilevsky.exchanger.dataaccess.ExchangeRateDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.datamodel.Currency_;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate_;

@Repository
public class ExchangeRateDaoImpl extends AbstractDaoImpl<ExchangeRate, Long> implements ExchangeRateDao {

	protected ExchangeRateDaoImpl() {
		super(ExchangeRate.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ExchangeRate> find(ExchangeRateFilter filter) {
		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ExchangeRate> cq = cb.createQuery(ExchangeRate.class);

		Root<ExchangeRate> from = cq.from(ExchangeRate.class);

		// set selection
		cq.select(from);

		if (filter.getConversion()!=null){
			Predicate conFromExchRateEqualCondition = cb.gt(from.get(ExchangeRate_.conversion),1.5);
			cq.where(conFromExchRateEqualCondition);
		}
		
		if (((filter.getCurrencyFrom()) != null)&&((filter.getCurrencyTo()) != null)&&(filter.getDateCurrency() != null)) {
			Predicate curFromExchRateEqualCondition = cb.equal(from.get(ExchangeRate_.currencyFrom).get(Currency_.name),
					filter.getCurrencyFrom());
			Predicate curToExchRateEqualCondition = cb.equal(from.get(ExchangeRate_.currencyTo).get(Currency_.name),
					filter.getCurrencyTo());
			Predicate dateExchRateEqualCondition = cb.equal(from.get(ExchangeRate_.dateCourse),
					filter.getDateCurrency());
			cq.where(cb.and(curFromExchRateEqualCondition, curToExchRateEqualCondition,dateExchRateEqualCondition));
		}
		
		// set fetching
		if (filter.isFetchCredentials()) {
			from.fetch(ExchangeRate_.currencyFrom, JoinType.LEFT);
			from.fetch(ExchangeRate_.currencyTo, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<ExchangeRate> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<ExchangeRate> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public Long count(ExchangeRateFilter exchangeRateFilter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ExchangeRate> from = cq.from(ExchangeRate.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}
}
