package com.practice.transaction.service;

import com.practice.transaction.exception.InvalidCovergaeAmountException;
import com.practice.transaction.model.EmployeeHealthInsurance;

public interface HealthInsuranceService {
	void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) throws InvalidCovergaeAmountException;

	void deleteEmployeeHealthInsuranceByEmpId(Long empid);

	void deleteEmployeeHealthInsuranceById(Long empid);
}