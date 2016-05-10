package com.epam.vasilevsky.exchanger.dataaccess.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.epam.vasilevsky.exchanger.dataaccess.UserCredentialsDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.UserCredentialsFilter;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.datamodel.UserCredentials_;

@Repository
public class UserCredentialsDaoImpl extends AbstractDaoImpl<UserCredentials, Long> implements UserCredentialsDao {

	protected UserCredentialsDaoImpl() {
		super(UserCredentials.class);
	}

	@Override
	public List<UserCredentials> find(UserCredentialsFilter filter) {

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserCredentials> cq = cb.createQuery(UserCredentials.class);
		Root<UserCredentials> from = cq.from(UserCredentials.class);
		cq.select(from);

		if ((filter.getLogin() != null) & (filter.getPassword()) != null) {
			Predicate loginEqualCondition = cb.equal(from.get(UserCredentials_.login), filter.getLogin());
			Predicate passwordEqualCondition = cb.equal(from.get(UserCredentials_.password), filter.getPassword());
			cq.where(cb.and(loginEqualCondition, passwordEqualCondition));
		}
		TypedQuery<UserCredentials> q = em.createQuery(cq);

		List<UserCredentials> allitems = q.getResultList();
		return allitems;
	}
}