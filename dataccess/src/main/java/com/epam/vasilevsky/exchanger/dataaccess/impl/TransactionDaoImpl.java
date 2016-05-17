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

import com.epam.vasilevsky.exchanger.dataaccess.TransactionDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.TransactionFilter;

import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.datamodel.Transaction_;

@Repository
public class TransactionDaoImpl extends AbstractDaoImpl<Transaction,Long> implements TransactionDao{

	protected TransactionDaoImpl() {
		super(Transaction.class);
	}
	
	@Override
    public Long count(TransactionFilter filter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Transaction> from = cq.from(Transaction.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
    }

	@Override
	public List<Transaction> find(TransactionFilter filter) {
		EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);

        Root<Transaction> from = cq.from(Transaction.class);

        // set selection
        cq.select(from);

        if (filter.getDateTransaction() != null) {
            Predicate dateTransactionEqualCondition = cb.equal(from.get(Transaction_.dateOperation), filter.getDateTransaction());
            //Predicate lNameEqualCondition = cb.equal(from.get(UserProfile_.lastName), filter.getUserName());
            cq.where((dateTransactionEqualCondition));
        }
        // set fetching
        if (filter.isFetchCredentials()) {
            from.fetch(Transaction_.operation, JoinType.LEFT);
        }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<Transaction> q = em.createQuery(cq);

        // set paging
        setPaging(filter, q);

        // set execute query
        List<Transaction> allitems = q.getResultList();
        return allitems;
	}

	

}
