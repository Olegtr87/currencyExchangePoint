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

import com.epam.vasilevsky.exchanger.dataaccess.BalanceDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.BalanceFilter;
import com.epam.vasilevsky.exchanger.dataaccess.filters.UserFilter;
import com.epam.vasilevsky.exchanger.datamodel.Balance;
import com.epam.vasilevsky.exchanger.datamodel.Balance_;
import com.epam.vasilevsky.exchanger.datamodel.Currency;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials_;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile;
import com.epam.vasilevsky.exchanger.datamodel.UserProfile_;

@Repository
public class BalanceDaoImpl extends AbstractDaoImpl<Balance,Long> implements BalanceDao{

	protected BalanceDaoImpl() {
		super(Balance.class);
	}
	
	@Override
	public List<Balance> find(BalanceFilter filter) {
		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Balance> cq = cb.createQuery(Balance.class);

		Root<Balance> from = cq.from(Balance.class);

		// set selection
		cq.select(from);

		// set fetching
		if (filter.isFetchCredentials()) {
			from.fetch(Balance_.currency, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Balance> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<Balance> allitems = q.getResultList();
		return allitems;
	}

	@Override
    public Long count(BalanceFilter filter) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Balance> from = cq.from(Balance.class);

        // set selection
        cq.select(cb.count(from));


        TypedQuery<Long> q = em.createQuery(cq);

        // set execute query
        return q.getSingleResult();
    }

    
}
