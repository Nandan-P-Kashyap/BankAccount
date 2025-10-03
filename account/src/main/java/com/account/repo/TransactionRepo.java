package com.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.entities.TransactionEntity;
@Repository

public interface TransactionRepo extends JpaRepository<TransactionEntity, Long>{

}
