package com.netent.employee.service;

import javax.validation.Valid;

import com.netent.employee.base.EmployeeBulkRequest;
import com.netent.employee.base.EmployeeBulkResponse;
import com.netent.employee.base.EmployeeRequest;
import com.netent.employee.base.EmployeeResponse;
import com.netent.employee.base.SearchEmployeeResponse;

public interface EmployeeService {

	EmployeeResponse addEmployee(@Valid EmployeeRequest employeeRequest) throws Exception;

	SearchEmployeeResponse searchEmployees(String name, Integer age);

	EmployeeBulkResponse addEmployeeInBulk(@Valid EmployeeBulkRequest employeeRequestBulk);

}
