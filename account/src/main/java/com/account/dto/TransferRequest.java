package com.account.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferRequest {
	@NotBlank
	private String fromAccount;

	@NotBlank
	private String toAccount;

	@Positive
	private BigDecimal amount;
}
