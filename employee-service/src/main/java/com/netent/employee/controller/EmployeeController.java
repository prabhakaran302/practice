package com.netent.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netent.employee.base.EmployeeBulkRequest;
import com.netent.employee.base.EmployeeBulkResponse;
import com.netent.employee.base.EmployeeRequest;
import com.netent.employee.base.EmployeeResponse;
import com.netent.employee.base.SearchEmployeeResponse;
import com.netent.employee.service.EmployeeService;
import com.netent.employee.util.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = Constants.Api.ADD)
	public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest,
			BindingResult bindingResult) throws BindException {
		try {
			if (bindingResult.hasErrors()) {
				throw new BindException(bindingResult);
			} else {
				return new ResponseEntity<EmployeeResponse>(employeeService.addEmployee(employeeRequest),
						HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = Constants.Api.SEARCH)
	public ResponseEntity<SearchEmployeeResponse> viewEmployee(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, @PathVariable(value = "name", required = false) String name,
			@RequestParam(value = "age", required = false) Integer age) {
		SearchEmployeeResponse response = employeeService.searchEmployees(name, age);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = Constants.Api.ADD_BULK)
	public ResponseEntity<?> addAllEmployee(@Valid @RequestBody EmployeeBulkRequest employeeRequestBulk,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<String>("Error in request", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<EmployeeBulkResponse>(employeeService.addEmployeeInBulk(employeeRequestBulk),
						HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
