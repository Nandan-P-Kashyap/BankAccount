package com.account.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION", schema = "C##ACCOUNT_DB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq_gen")
    @SequenceGenerator(name = "transaction_seq_gen", sequenceName = "SEQ_TRANSACTION_ID", allocationSize = 1)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;

    @Column(name = "TARGET_ACCOUNT_ID")
    private Long targetAccountId;

    @Column(name = "TRANSACTION_TYPE", length = 20, nullable = false)
    private String transactionType;

    @Column(name = "AMOUNT", precision = 18, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(name = "PERFORMED_BY", nullable = false)
    private Long performedBy;
}
