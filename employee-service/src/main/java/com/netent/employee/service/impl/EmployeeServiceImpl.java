package com.netent.employee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.netent.employee.base.EmployeeBulkRequest;
import com.netent.employee.base.EmployeeBulkResponse;
import com.netent.employee.base.EmployeeRequest;
import com.netent.employee.base.EmployeeResponse;
import com.netent.employee.base.SearchEmployeeResponse;
import com.netent.employee.dto.EmployeePayRollSearchDTO;
import com.netent.employee.exception.PayRollServiceException;
import com.netent.employee.model.Employee;
import com.netent.employee.repository.EmployeeRepository;
import com.netent.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PayRollService payRollService;

	@Override
	public EmployeeResponse addEmployee(@Valid EmployeeRequest employeeRequest) throws Exception {
		Employee emp = new Employee();
		List<Employee> empList = employeeRepository.findByNameStartsWith(employeeRequest.getEmployee_name());
		if (empList.size() == 0)
			emp.setName(employeeRequest.getEmployee_name());
		else
			emp.setName(employeeRequest.getEmployee_name() + (empList.size()));
		emp.setAge(employeeRequest.getEmployee_age());
		emp.setSalary(employeeRequest.getEmployee_salary());
		EmployeeResponse response = new EmployeeResponse();
		Map<String, String> map = response.getMap();
		map.put("Employee Name", emp.getName());
		try {
			Long empPayRollId = payRollService.createEmployee(emp);
			if (empPayRollId != -1) {
				emp.setId(empPayRollId);
				emp = employeeRepository.save(emp);
				map.put("Id ", String.valueOf(empPayRollId));
				response.setMessage("Employee saved succesfully with Id " + emp.getId());
				response.setStatus(HttpStatus.CREATED);
			} else {
				throw new PayRollServiceException("Payroll Service Not able to save employee");
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage("Not able to save employee with Id  " + emp.getId() + " " + e.getMessage());
			throw e;
		}
		return response;
	}

	@Override
	public SearchEmployeeResponse searchEmployees(String name, Integer age) {
		SearchEmployeeResponse searchResponse = new SearchEmployeeResponse();
		List<Employee> searchEmpList = new ArrayList<Employee>();
		try {
			List<EmployeePayRollSearchDTO> emplListPayroll = payRollService.getEmployees();
			List<Employee> empServiceList = employeeRepository.findByNameOrAge(name, age);

			searchResponse.setEmpList(searchEmpList);

			if (emplListPayroll != null && !emplListPayroll.isEmpty() && empServiceList != null
					&& !empServiceList.isEmpty()) {
				Map<Long, EmployeePayRollSearchDTO> payRollResult = emplListPayroll.stream()
						.collect(Collectors.toMap(empDTO -> empDTO.getId(), empDTO -> empDTO));
				Map<Long, Employee> empServiceResult = empServiceList.stream()
						.collect(Collectors.toMap(emp -> emp.getId(), emp -> emp));
				empServiceResult.forEach((empId, emp) -> {
					if (payRollResult.get(empId) != null) {
						searchEmpList.add(emp);
					}
				});
			}
			searchResponse.setStatus(HttpStatus.OK);
			searchResponse.setMessage("Total No of Employees Present " + searchEmpList.size());
		} catch (Exception e) {
			searchResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			searchResponse.setMessage(e.getMessage());
		}
		return searchResponse;
	}

	@Override
	public EmployeeBulkResponse addEmployeeInBulk(@Valid EmployeeBulkRequest empBulkReq) {
		List<EmployeeRequest> empList = empBulkReq.getEmpRequestList();
		EmployeeBulkResponse eBulkResponse = new EmployeeBulkResponse();
		Map<String, String> map = new HashMap<>();
		eBulkResponse.setMap(map);
		empList.forEach(eReq -> {
			try {
				addEmployee(eReq);
				map.put(String.valueOf(eReq.getEmployee_name()), "Added");
			} catch (Exception e) {
				e.printStackTrace();
				map.put(String.valueOf(eReq.getEmployee_name()), "Not Added " + e.getMessage());
			}
		});
		eBulkResponse.setTotalSaved(eBulkResponse.getMap().size());
		eBulkResponse.setTotalFailed(empBulkReq.getEmpRequestList().size() - eBulkResponse.getTotalSaved());
		return eBulkResponse;
	}

}
