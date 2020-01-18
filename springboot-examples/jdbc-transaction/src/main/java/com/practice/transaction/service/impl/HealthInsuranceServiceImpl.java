package com.practice.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.transaction.model.EmployeeHealthInsurance;
import com.practice.transaction.repository.HealthInsuranceRepository;
import com.practice.transaction.service.HealthInsuranceService;

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService {

	@Autowired
	HealthInsuranceRepository healthInsuranceRepository;

	@Override
	public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) {
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