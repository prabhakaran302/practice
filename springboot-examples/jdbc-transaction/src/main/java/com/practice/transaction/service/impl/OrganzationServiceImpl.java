package com.practice.transaction.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.practice.transaction.dto.OrganizationResponse;
import com.practice.transaction.exception.InvalidCovergaeAmountException;
import com.practice.transaction.model.Employee;
import com.practice.transaction.model.EmployeeHealthInsurance;
import com.practice.transaction.service.EmployeeService;
import com.practice.transaction.service.HealthInsuranceService;
import com.practice.transaction.service.OrganizationService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = InvalidCovergaeAmountException.class, isolation = Isolation.SERIALIZABLE)
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	HealthInsuranceService healthInsuranceService;

	@Override
	public OrganizationResponse joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance)
			throws InvalidCovergaeAmountException {
		employeeService.insertEmployee(employee);
		if (employee.getEmpName().equalsIgnoreCase("emp1")) {
			throw new RuntimeException("thowing exception to test transaction rollback");
		}
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
		return createResponse(employee, employeeHealthInsurance);
	}

	private OrganizationResponse createResponse(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		OrganizationResponse response = new OrganizationResponse();
		response.setClaimAmount(employeeHealthInsurance.getCoverageAmount()
				.multiply(new BigDecimal(employeeHealthInsurance.getMonths())));
		response.setEmployeeId(employee.getId());
		response.setName(employee.getEmpName());
		return response;
	}

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmployeeById(employee.getId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}