package com.practice.transaction.service;

import com.practice.transaction.model.Employee;

public interface EmployeeService {
	void insertEmployee(Employee emp);
	void deleteEmployeeById(Long empid);
}