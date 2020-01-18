package com.practice.transaction.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.transaction.dto.OrganizationResponse;
import com.practice.transaction.dto.OrgansizationRequest;
import com.practice.transaction.exception.InvalidCovergaeAmountException;
import com.practice.transaction.model.Employee;
import com.practice.transaction.model.EmployeeHealthInsurance;
import com.practice.transaction.service.OrganizationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/organization")
@RequiredArgsConstructor
public class TransactionController {

	@Autowired
	private OrganizationService organizationService;

	@PostMapping
	public ResponseEntity<OrganizationResponse> create(@Valid @RequestBody OrgansizationRequest organsizationRequest)
			throws InvalidCovergaeAmountException {

		Employee emp = new Employee();
		emp.setId(organsizationRequest.getEmployeeId());
		emp.setEmpName(organsizationRequest.getEmpName());

		EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId(organsizationRequest.getEmployeeId());
		employeeHealthInsurance.setHealthInsuranceSchemeName(organsizationRequest.getHealthInsuranceSchemeName());
		employeeHealthInsurance.setCoverageAmount(organsizationRequest.getCoverageAmount());
		employeeHealthInsurance.setMonths(organsizationRequest.getMonth());

		return new ResponseEntity<OrganizationResponse>(
				organizationService.joinOrganization(emp, employeeHealthInsurance), HttpStatus.CREATED);
	}
}
