package com.practice.transaction.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrgansizationRequest {
	private Long employeeId;
	private String empName;
	private int month;
	private String healthInsuranceSchemeName;
	private BigDecimal coverageAmount;
}
