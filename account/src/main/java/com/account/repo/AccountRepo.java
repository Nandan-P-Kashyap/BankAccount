package com.account.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.entities.AccountEntity;

@Repository

public interface AccountRepo extends JpaRepository<AccountEntity, Long>{
	Optional<AccountEntity> findByAccountNumber(Long accountNumber);
}
