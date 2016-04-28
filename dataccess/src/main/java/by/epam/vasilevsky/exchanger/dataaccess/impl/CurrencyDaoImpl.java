package by.epam.vasilevsky.exchanger.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import by.epam.vasilevsky.exchanger.dataaccess.CurrencyDao;
import by.epam.vasilevsky.exchanger.dataaccess.filters.CurrencyFilter;
import by.epam.vasilevsky.exchanger.datamodel.Currency;
import by.epam.vasilevsky.exchanger.datamodel.Currency_;

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
            //Predicate lNameEqualCondition = cb.equal(from.get(UserProfile_.lastName), filter.getUserName());
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
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<Currency> allitems = q.getResultList();
        return allitems;
	}

}
