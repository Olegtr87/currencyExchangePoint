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

import com.epam.vasilevsky.exchanger.dataaccess.BankAccountUserDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.BankAccountUserFilter;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser_;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials_;

@Repository
public class BankAccountUserDaoImpl extends AbstractDaoImpl<BankAccountUser, Long> implements BankAccountUserDao {
	protected BankAccountUserDaoImpl() {
		super(BankAccountUser.class);
	}

	@Override
	public List<BankAccountUser> find(BankAccountUserFilter filter) {
		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<BankAccountUser> cq = cb.createQuery(BankAccountUser.class);

		Root<BankAccountUser> from = cq.from(BankAccountUser.class);

		// set selection
		cq.select(from);

		if (filter.getUserCredentials() != null && filter.getCurrency()==null) {
			Predicate loginEqualCondition = cb.equal(from.get(BankAccountUser_.user).get(UserCredentials_.id),
					filter.getUserCredentials().getId());
			cq.where(cb.and(loginEqualCondition));
		}
		
		if (filter.getUserCredentials() != null && filter.getCurrency()!=null) {
			Predicate loginEqualCondition = cb.equal(from.get(BankAccountUser_.user).get(UserCredentials_.id),
					filter.getUserCredentials().getId());
			Predicate currencyEqualCondition = cb.equal(from.get(BankAccountUser_.currency),
					filter.getCurrency());
			cq.where(cb.and(loginEqualCondition,currencyEqualCondition));
		}

		// set fetching
		if (filter.isFetchCredentials()) {
			from.fetch(BankAccountUser_.user, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<BankAccountUser> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<BankAccountUser> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public Long count(BankAccountUserFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<BankAccountUser> from = cq.from(BankAccountUser.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}

}