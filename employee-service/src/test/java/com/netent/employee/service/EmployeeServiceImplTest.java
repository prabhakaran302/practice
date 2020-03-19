package com.netent.employee.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.netent.employee.base.EmployeeBulkRequest;
import com.netent.employee.base.EmployeeBulkResponse;
import com.netent.employee.base.EmployeeRequest;
import com.netent.employee.base.EmployeeResponse;
import com.netent.employee.base.SearchEmployeeResponse;
import com.netent.employee.dto.EmployeePayRollSearchDTO;
import com.netent.employee.exception.PayRollServiceException;
import com.netent.employee.model.Employee;
import com.netent.employee.repository.EmployeeRepository;
import com.netent.employee.service.impl.EmployeeServiceImpl;
import com.netent.employee.service.impl.PayRollService;

@SpringBootTest
public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private PayRollService payRollService;

	@InjectMocks
	private EmployeeService employeeService = new EmployeeServiceImpl();

	@Test
	public void testAddEmployee() {
		@Valid
		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setEmployee_age(31);
		employeeRequest.setEmployee_name("rohit");
		employeeRequest.setEmployee_salary(290500L);

		try {

			List<Employee> empList = new ArrayList<Employee>();
			when(employeeRepository.findByNameStartsWith("rohit")).thenReturn(empList);

			Employee e = new Employee("rohit", 31, 290500L);
			when(payRollService.createEmployee(e)).thenReturn(85L);

			Employee e1 = new Employee("rohit", 31, 290500L);
			e1.setId(85L);
			when(employeeRepository.save(e1)).thenReturn(e1);

			EmployeeResponse response = employeeService.addEmployee(employeeRequest);
			Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddEmployeeAppendNumber() {
		@Valid
		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setEmployee_age(31);
		employeeRequest.setEmployee_name("rohit");
		employeeRequest.setEmployee_salary(290500L);

		try {

			List<Employee> empList = new ArrayList<Employee>();
			Employee e2 = new Employee();
			empList.add(e2);
			when(employeeRepository.findByNameStartsWith("rohit")).thenReturn(empList);

			Employee e = new Employee("rohit1", 31, 290500L);
			when(payRollService.createEmployee(e)).thenReturn(95L);

			Employee e1 = new Employee("rohit1", 31, 290500L);
			e1.setId(95L);
			when(employeeRepository.save(e1)).thenReturn(e1);

			EmployeeResponse response = employeeService.addEmployee(employeeRequest);
			Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
		} catch (Exception e) {

		}
	}

	@Test()
	public void call2_should_throw_a_WantedException__not_call1() {
		@Valid
		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setEmployee_age(31);
		employeeRequest.setEmployee_name("rohit");
		employeeRequest.setEmployee_salary(290500L);

		List<Employee> empList = new ArrayList<Employee>();
		Employee e2 = new Employee();
		empList.add(e2);
		when(employeeRepository.findByNameStartsWith("rohit")).thenReturn(empList);

		Employee e = new Employee("rohit1", 31, 290500L);
		when(payRollService.createEmployee(e)).thenReturn((long) -1);

		Throwable exception = assertThrows(PayRollServiceException.class,
				() -> employeeService.addEmployee(employeeRequest));
		Assert.assertEquals("Payroll Service Not able to save employee", exception.getMessage());
	}

	@Test()
	public void payrollService_should_throw_a_WantedException__not_call1() {
		@Valid
		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setEmployee_age(31);
		employeeRequest.setEmployee_name("rohit");
		employeeRequest.setEmployee_salary(290500L);

		List<Employee> empList = new ArrayList<Employee>();
		Employee e2 = new Employee();
		empList.add(e2);
		when(employeeRepository.findByNameStartsWith("rohit")).thenReturn(empList);

		Employee e = new Employee("rohit1", 31, 290500L);
		when(payRollService.createEmployee(e)).thenReturn((long) -1);

		Throwable exception = assertThrows(PayRollServiceException.class,
				() -> employeeService.addEmployee(employeeRequest));
		Assert.assertEquals("Payroll Service Not able to save employee", exception.getMessage());
	}

	@Test
	public void testSearchEmployees() {
		List<EmployeePayRollSearchDTO> empPayRollDTO = new ArrayList<EmployeePayRollSearchDTO>();
		EmployeePayRollSearchDTO es = new EmployeePayRollSearchDTO();
		es.setEmployee_age(30);
		es.setEmployee_name("rohit");
		es.setEmployee_salary(25000L);
		es.setId(101L);
		empPayRollDTO.add(es);

		es = new EmployeePayRollSearchDTO();
		es.setEmployee_age(29);
		es.setEmployee_name("virat");
		es.setEmployee_salary(50500L);
		es.setId(102L);
		empPayRollDTO.add(es);

		when(payRollService.getEmployees()).thenReturn(empPayRollDTO);

		List<Employee> empServiceList = new ArrayList<Employee>();
		Employee e = new Employee("virat", 29, 50500L);
		e.setId(101L);
		empServiceList.add(e);
		when(employeeRepository.findByNameOrAge("virat", 29)).thenReturn(empServiceList);

		SearchEmployeeResponse resp = employeeService.searchEmployees("virat", 29);
		Assert.assertEquals(HttpStatus.OK, resp.getStatus());
		Assert.assertEquals(1, resp.getEmpList().size());
	}

	@Test
	public void testSearchEmployeesInternalError() {
		List<EmployeePayRollSearchDTO> empPayRollDTO = new ArrayList<EmployeePayRollSearchDTO>();
		EmployeePayRollSearchDTO es = new EmployeePayRollSearchDTO();
		es.setEmployee_age(30);
		es.setEmployee_name("rohit");
		es.setEmployee_salary(25000L);
		empPayRollDTO.add(es);

		es = new EmployeePayRollSearchDTO();
		es.setEmployee_age(29);
		es.setEmployee_name("virat");
		es.setEmployee_salary(50500L);
		empPayRollDTO.add(es);

		when(payRollService.getEmployees()).thenReturn(empPayRollDTO);

		List<Employee> empServiceList = new ArrayList<Employee>();
		Employee e = new Employee("virat", 29, 50500L);
		empServiceList.add(e);
		when(employeeRepository.findByNameOrAge("virat", 29)).thenReturn(empServiceList);

		SearchEmployeeResponse resp = employeeService.searchEmployees("virat", 29);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatus());
	}

	@Test
	public void testAddEmployeeInBulk() {
		EmployeeBulkRequest empBulkReq = new EmployeeBulkRequest();
		List<EmployeeRequest> empRequestList = new ArrayList<EmployeeRequest>();
		
		EmployeeRequest er = new EmployeeRequest();
		er.setEmployee_name("rohit");
		er.setEmployee_age(32);
		er.setEmployee_salary(287500L);
		empRequestList.add(er);
		
		er = new EmployeeRequest();
		er.setEmployee_name("virat");
		er.setEmployee_age(31);
		er.setEmployee_salary(325500L);
		empRequestList.add(er);
		
		empBulkReq.setEmpRequestList(empRequestList);
		
		Employee e = new Employee("rohit", 32, 287500L);
		when(payRollService.createEmployee(e)).thenReturn(85L);
		
		Employee er1 = new Employee("rohit", 32, 287500L);
		er1.setId(85L);
		when(employeeRepository.save(er1)).thenReturn(er1);
		
		Employee e1 = new Employee("virat", 31, 325500L);
		when(payRollService.createEmployee(e1)).thenReturn(95L);
		
		Employee er2 = new Employee("virat", 31, 325500L);
		er2.setId(95L);
		when(employeeRepository.save(er2)).thenReturn(er2);
		
		EmployeeBulkResponse ebr = employeeService.addEmployeeInBulk(empBulkReq);
		Assert.assertEquals(true, ebr.getMap().containsKey("virat"));
		Assert.assertEquals(2, ebr.getTotalSaved());
		Assert.assertEquals(0, ebr.getTotalFailed());
	}
}
