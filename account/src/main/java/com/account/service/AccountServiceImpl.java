package com.account.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.entities.AccountEntity;
import com.account.entities.TransactionEntity;
import com.account.exception.InsufficientFundsException;
import com.account.exception.NotFoundException;
import com.account.repo.AccountRepo;
import com.account.repo.TransactionRepo;

import jakarta.transaction.Transactional;

@Service


public class AccountServiceImpl implements AccountService {
	
	private AccountRepo acc;
	private TransactionRepo tran;
	
	@Autowired
	public AccountServiceImpl(AccountRepo acc, TransactionRepo tran) {
		this.acc = acc;
		this.tran = tran;
	}
	@Override
	public AccountEntity createAccount(AccountEntity newaccount) {
		if(newaccount.getBalance() == null) {
			newaccount.setBalance(BigDecimal.ZERO);
		}
		return acc.save(newaccount);
	}
	@Override
	public AccountEntity getByAccountNumber(String accountNumber) {
		return acc.findByAccountNumber(accountNumber)
				.orElseThrow(()->new NotFoundException("Account not found: " + accountNumber));
		
	}
	
	@Override
	public AccountEntity updateAccount(String accountNumber, AccountEntity update) {
		AccountEntity existing = getByAccountNumber(accountNumber);
		existing.setAccountHolder(update.getAccountHolder());
		existing.setAccountType(update.getAccountType());
		return acc.save(existing);
	}

	@Override
	public void deleteAccount(String accountNumber) {
		AccountEntity a = getByAccountNumber(accountNumber);
		acc.delete(a);
	}
	@Override
	public List<AccountEntity> listAll() {
		return acc.findAll();
	}
	@Override
	@Transactional
	public AccountEntity deposit(String accountNumber, BigDecimal amount) {
		
		System.out.println("Depositing to accountNo: " + accountNumber);
		if (amount.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Amount must be > 0");
		AccountEntity a = getByAccountNumber(accountNumber);
		a.setBalance(a.getBalance().add(amount));
		AccountEntity UpdatedAcc =  acc.save(a);
		
		TransactionEntity newTransaction = TransactionEntity.builder()
				.accountId(a.getId())
				.targetAccountId(null)
				.transactionType("DEPOSIT")
				.amount(a.getBalance())
				.timestamp(LocalDateTime.now())
				.performedBy(a.getUserId())
				.build();
		tran.save(newTransaction);
		return UpdatedAcc;
				
	}
	@Override
	public AccountEntity withdraw(String accountNumber, BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Amount must be > 0");
		AccountEntity a = getByAccountNumber(accountNumber);
		if (a.getBalance().compareTo(amount) < 0)
			throw new InsufficientFundsException("Insufficient funds");
		a.setBalance(a.getBalance().subtract(amount));
		
		AccountEntity updatedAccount =  acc.save(a);
		 TransactionEntity newTransaction = TransactionEntity.builder()
		            .accountId(a.getId())
		            .targetAccountId(null) // For withdrawal, target is null
		            .transactionType("WITHDRAWAL")
		            .amount(amount)
		            .timestamp(LocalDateTime.now())
		            .performedBy(a.getUserId())
		            .build();
		    tran.save(newTransaction);

		    return updatedAccount;
	}
	@Override
	public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
		if(fromAccountNumber.equals(toAccountNumber))
			throw new IllegalArgumentException("Can't transfer to same account");
		AccountEntity from = getByAccountNumber(fromAccountNumber);
		AccountEntity to = getByAccountNumber(toAccountNumber);
		
		if(amount.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Amount must be > 0");
		if(from.getBalance().compareTo(amount) <= 0)
			throw new InsufficientFundsException("Insufficient funds");
		
		from.setBalance(from.getBalance().subtract(amount));
		to.setBalance(to.getBalance().add(amount));
		
		acc.save(from);
		acc.save(to);
		
		TransactionEntity tranFrom = TransactionEntity.builder()
	            .accountId(from.getId())
	            .targetAccountId(to.getId())
	            .transactionType("TRANSFER")
	            .amount(amount)
	            .timestamp(LocalDateTime.now())
	            .performedBy(from.getUserId())
	            .build();
	    tran.save(tranFrom);
			
		
	}


}
