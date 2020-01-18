package com.practice.transaction.service;

import com.practice.transaction.dto.OrganizationResponse;
import com.practice.transaction.exception.InvalidCovergaeAmountException;
import com.practice.transaction.model.Employee;
import com.practice.transaction.model.EmployeeHealthInsurance;

public interface OrganizationService {

	public OrganizationResponse joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) throws InvalidCovergaeAmountException;

	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

}
