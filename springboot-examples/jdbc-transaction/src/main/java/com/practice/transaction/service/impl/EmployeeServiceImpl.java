package com.practice.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.transaction.model.Employee;
import com.practice.transaction.repository.EmployeeRepository;
import com.practice.transaction.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void insertEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(Long empid) {
		employeeRepository.deleteById(empid);
	}

}