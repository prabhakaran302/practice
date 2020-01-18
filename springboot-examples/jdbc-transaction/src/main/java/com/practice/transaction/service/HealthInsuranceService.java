package com.practice.transaction.service;

import com.practice.transaction.model.EmployeeHealthInsurance;

public interface HealthInsuranceService {
	void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance);

	void deleteEmployeeHealthInsuranceByEmpId(Long empid);

	void deleteEmployeeHealthInsuranceById(Long empid);
}