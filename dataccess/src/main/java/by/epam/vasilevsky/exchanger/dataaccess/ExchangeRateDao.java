package by.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;
import by.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import by.epam.vasilevsky.exchanger.datamodel.ExchangeRate;

public interface ExchangeRateDao extends AbstractDao<ExchangeRate, Long>{
	
	List<ExchangeRate> find(ExchangeRateFilter filter);
}
