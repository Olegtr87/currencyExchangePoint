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

import com.epam.vasilevsky.exchanger.dataaccess.OperationDao;
import com.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;

import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Operation_;

@Repository
public class OperationDaoImpl extends AbstractDaoImpl<Operation,Long> implements OperationDao{

	protected OperationDaoImpl() {
		super(Operation.class);
	}

	@Override
	public List<Operation> find(OperationFilter filter) {
		EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Operation> cq = cb.createQuery(Operation.class);

        Root<Operation> from = cq.from(Operation.class);

        // set selection
        cq.select(from);

        if (filter.getOperationName() != null) {
            Predicate oNameEqualCondition = cb.equal(from.get(Operation_.name), filter.getOperationName());
            //Predicate lNameEqualCondition = cb.equal(from.get(UserProfile_.lastName), filter.getUserName());
            cq.where((oNameEqualCondition));
        }
        // set fetching
//        if (filter.isFetchCredentials()) {
//            from.fetch(Operation_., JoinType.LEFT);
//        }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<Operation> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<Operation> allitems = q.getResultList();
        return allitems;
	}

	

}
