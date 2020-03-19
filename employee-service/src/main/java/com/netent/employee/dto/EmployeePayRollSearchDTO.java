package com.netent.employee.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePayRollSearchDTO {
	private Long id;
	
	@NotNull(message = "Name cannot be empty")
	private String employee_name;

	@Positive(message = "Age cannot be negative")
	@Min(18)
	@Max(100)
	private Integer employee_age;

	@Positive(message = "Salary cannot be negative")
	private Long employee_salary;
}
