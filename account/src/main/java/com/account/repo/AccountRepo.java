package com.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.entities.AccountEntity;
import java.util.Optional;

@Repository

public interface AccountRepo extends JpaRepository<AccountEntity, Long>{
	Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
