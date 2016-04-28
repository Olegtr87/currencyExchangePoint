package by.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;
import by.epam.vasilevsky.exchanger.dataaccess.filters.OperationFilter;
import by.epam.vasilevsky.exchanger.datamodel.Operation;

public interface OperationDao extends AbstractDao<Operation, Long>{

	List<Operation> find(OperationFilter filter);
}
