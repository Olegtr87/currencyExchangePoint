package com.epam.vasilevsky.exchanger.service;

import java.util.List;

import javax.transaction.Transactional;

import com.epam.vasilevsky.exchanger.dataaccess.filters.BankAccountUserFilter;
import com.epam.vasilevsky.exchanger.datamodel.BankAccountUser;

public interface BankUserAccountService {

	List<BankAccountUser> findBankUserAccount(BankAccountUserFilter filter);
	
	Long count(BankAccountUserFilter filter);
	
	@Transactional
    void update(BankAccountUser bankAccountUser);
	
	@Transactional
	void add(BankAccountUser bankAccountUser);

}