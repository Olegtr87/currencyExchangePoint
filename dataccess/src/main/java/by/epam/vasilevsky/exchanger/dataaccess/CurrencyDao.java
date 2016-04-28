package by.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;
import by.epam.vasilevsky.exchanger.dataaccess.filters.CurrencyFilter;
import by.epam.vasilevsky.exchanger.datamodel.Currency;

public interface CurrencyDao extends AbstractDao<Currency, Long>{
	
	List<Currency> find(CurrencyFilter filter);
}
