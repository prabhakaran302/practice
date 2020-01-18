package com.practice.transaction.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.transaction.exception.InvalidCovergaeAmountException;
import com.practice.transaction.model.EmployeeHealthInsurance;
import com.practice.transaction.repository.HealthInsuranceRepository;
import com.practice.transaction.service.HealthInsuranceService;

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService {

	@Autowired
	HealthInsuranceRepository healthInsuranceRepository;

	@Override
	public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance)
			throws InvalidCovergaeAmountException {
		if (employeeHealthInsurance.getCoverageAmount().compareTo(new BigDecimal(12000)) > 0)
			throw new InvalidCovergaeAmountException("Amount greater than 12000");
		healthInsuranceRepository.save(employeeHealthInsurance);
	}

	@Override
	public void deleteEmployeeHealthInsuranceById(Long id) {
		healthInsuranceRepository.deleteById(id);
	}

	@Override
	public void deleteEmployeeHealthInsuranceByEmpId(Long empid) {
		healthInsuranceRepository.deleteEmployeeHealthInsuranceByEmpId(empid);
	}

}