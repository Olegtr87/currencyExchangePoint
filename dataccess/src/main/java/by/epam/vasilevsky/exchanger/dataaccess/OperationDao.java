package by.epam.vasilevsky.exchanger.dataaccess;

import by.epam.vasilevsky.exchanger.datamodel.Operation;

public interface OperationDao {

	Operation get(Long id);

	Operation save();

}
