package com.epam.vasilevsky.exchanger.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.CurrencyFilter;

import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.Currency_;

@Repository
public class CurrencyDaoImpl extends AbstractDaoImpl<Currency,Long> implements CurrencyDao{

	protected CurrencyDaoImpl() {
		super(Currency.class);
	}

	@Override
	public List<Currency> find(CurrencyFilter filter) {
		EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Currency> cq = cb.createQuery(Currency.class);

        Root<Currency> from = cq.from(Currency.class);

        // set selection
        cq.select(from);

        if (filter.getNameCurrency() != null) {
            Predicate currencyNameEqualCondition = cb.equal(from.get(Currency_.name), filter.getNameCurrency());
            cq.where((currencyNameEqualCondition));
        }
        // set fetching
//        if (filter.isFetchCredentials()) {
//            from.fetch(Currency_.name, JoinType.LEFT);
//        }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<Currency> q = em.createQuery(cq);

        // set paging
        setPaging(filter, q);

        // set execute query
        List<Currency> allitems = q.getResultList();
        return allitems;
	}

}
