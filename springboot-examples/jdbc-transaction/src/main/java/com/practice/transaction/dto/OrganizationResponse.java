package com.practice.transaction.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationResponse {
	private Long employeeId;
	private String name;
	private BigDecimal claimAmount;
}
