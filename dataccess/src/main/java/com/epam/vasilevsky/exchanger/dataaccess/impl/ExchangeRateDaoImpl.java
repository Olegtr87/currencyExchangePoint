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

import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate_;

@Repository
public class ExchangeRateDaoImpl extends AbstractDaoImpl<ExchangeRate,Long> implements ExchangeRateDao{

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

        if (filter.getDateCurrency() != null) {
            Predicate dateExchRateEqualCondition = cb.equal(from.get(ExchangeRate_.dateCourse), filter.getDateCurrency());
            //Predicate lNameEqualCondition = cb.equal(from.get(UserProfile_.lastName), filter.getUserName());
            cq.where((dateExchRateEqualCondition));
        }
        // set fetching
        if (filter.isFetchCredentials()) {
            from.fetch(ExchangeRate_.currencyFrom, JoinType.LEFT);
        }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<ExchangeRate> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<ExchangeRate> allitems = q.getResultList();
        return allitems;
	}

	
}
