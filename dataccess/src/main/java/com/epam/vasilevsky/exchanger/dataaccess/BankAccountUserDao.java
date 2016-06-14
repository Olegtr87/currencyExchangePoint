package com.epam.vasilevsky.exchanger.dataaccess;

import java.util.List;
import com.epam.vasilevsky.exchanger.dataaccess.filters.BankAccountUserFilter;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;

public interface BankAccountUserDao extends AbstractDao<BankAccountUser, Long>{
	
	List<BankAccountUser> find(BankAccountUserFilter filter);
	
	Long count(BankAccountUserFilter filter);
}