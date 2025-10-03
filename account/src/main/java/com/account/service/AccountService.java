package com.account.service;

import java.math.BigDecimal;
import java.util.List;

import com.account.entities.AccountEntity;

public interface AccountService {
	
	AccountEntity createAccount(AccountEntity account);

	AccountEntity getByAccountNumber(String accountNumber);

	AccountEntity updateAccount(String accountNumber, AccountEntity update);

	void deleteAccount(String accountNumber);

	List<AccountEntity> listAll();

	AccountEntity deposit(String accountNumber, BigDecimal amount);

	AccountEntity withdraw(String accountNumber, BigDecimal amount);

	void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount);

}
