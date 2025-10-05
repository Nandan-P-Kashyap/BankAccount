package com.account.service;

import java.math.BigDecimal;
import java.util.List;

import com.account.dto.NewUserDto;
import com.account.entities.AccountEntity;

public interface AccountService {
	
	AccountEntity createAccount(NewUserDto account, Long userId);

	AccountEntity getByAccountNumber(Long accountNumber);

	AccountEntity updateAccount(Long accountNumber, AccountEntity update);

	void deleteAccount(Long accountNumber);

	List<AccountEntity> listAll();

	AccountEntity deposit(Long accountNumber, BigDecimal amount);

	AccountEntity withdraw(Long accountNumber, BigDecimal amount);

	void transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal amount);

}
