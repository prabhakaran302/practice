package com.netent.employee.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.netent.employee.config.EmployeeConfig;
import com.netent.employee.dto.EmployeePayRollSearchDTO;
import com.netent.employee.model.Employee;
import com.netent.employee.service.impl.PayRollService;
import com.netent.employee.util.Constants;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = { EmployeeConfig.class }, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(properties = { "payroll.server.baseUri=http://dummy.restapiexample.com/api/v1/" })
public class PayRollServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	PayRollService payRollService = new PayRollService();

	@Test
	public void testCreate() {
		Employee emp = new Employee();
		emp.setName("rohit");
		emp.setAge(30);
		emp.setSalary(11000L);

		ReflectionTestUtils.setField(payRollService, "uri", "http://dummy.restapiexample.com/api/v1/");

		HttpHeaders headers = new HttpHeaders();
		String json = "{\"name\":\"rohit\",\"age\":30,\"salary\":11000}";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);

		String body = "{\r\n" + "    \"status\": \"success\",\r\n" + "    \"data\": {\r\n"
				+ "        \"name\": \"test\",\r\n" + "        \"salary\": \"123\",\r\n"
				+ "        \"age\": \"23\",\r\n" + "        \"id\": 30\r\n" + "    }\r\n" + "}";
		ResponseEntity<String> value = Mockito.spy(new ResponseEntity<String>(body, null, HttpStatus.CREATED));

		Mockito.when(restTemplate.exchange("http://dummy.restapiexample.com/api/v1/create", HttpMethod.POST, entity,
				String.class)).thenReturn(value);

		Long id = payRollService.createEmployee(emp);

		Assert.assertEquals(Long.valueOf(30), Long.valueOf(id));
	}

	@Test
	public void testCreateNotSaved() {
		Employee emp = new Employee();
		emp.setName("rohit");
		emp.setAge(30);
		emp.setSalary(11000L);

		ReflectionTestUtils.setField(payRollService, "uri", "http://dummy.restapiexample.com/api/v1/");

		HttpHeaders headers = new HttpHeaders();
		String json = "{\"name\":\"rohit\",\"age\":30,\"salary\":11000}";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);

		String body = null;
		ResponseEntity<String> value = Mockito.spy(new ResponseEntity<String>(body, null, HttpStatus.CREATED));

		Mockito.when(restTemplate.exchange("http://dummy.restapiexample.com/api/v1/create", HttpMethod.POST, entity,
				String.class)).thenReturn(value);

		Long id = payRollService.createEmployee(emp);

		Assert.assertEquals(Long.valueOf(-1), Long.valueOf(id));
	}

	@Test
	public void testCreateNotSavedDataNotNull() {
		Employee emp = new Employee();
		emp.setName("rohit");
		emp.setAge(30);
		emp.setSalary(11000L);

		ReflectionTestUtils.setField(payRollService, "uri", "http://dummy.restapiexample.com/api/v1/");

		HttpHeaders headers = new HttpHeaders();
		String json = "{\"name\":\"rohit\",\"age\":30,\"salary\":11000}";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);

		String body = "{\r\n" + "    \"status\": \"success\",\r\n" + "    \"data\": {\r\n"
				+ "        \"name\": \"test\",\r\n" + "        \"salary\": \"123\",\r\n"
				+ "        \"age\": \"23\",\r\n" + "        \"id\": 30\r\n" + "    }\r\n" + "}";
		ResponseEntity<String> value = Mockito.spy(new ResponseEntity<String>(body, null, HttpStatus.CREATED));

		Mockito.when(restTemplate.exchange("http://dummy.restapiexample.com/api/v1/create", HttpMethod.POST, entity,
				String.class)).thenReturn(value);

		Long id = payRollService.createEmployee(emp);

		Assert.assertEquals(Long.valueOf(30), Long.valueOf(id));
	}

	@Test
	public void testCreateNotSavedDataNull() {
		Employee emp = new Employee();
		emp.setName("rohit");
		emp.setAge(30);
		emp.setSalary(11000L);

		ReflectionTestUtils.setField(payRollService, "uri", "http://dummy.restapiexample.com/api/v1/");

		HttpHeaders headers = new HttpHeaders();
		String json = "{\"name\":\"rohit\",\"age\":30,\"salary\":11000}";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);

		String body = "{\"status\": \"success\"}";
		ResponseEntity<String> value = Mockito.spy(new ResponseEntity<String>(body, null, HttpStatus.CREATED));

		Mockito.when(restTemplate.exchange("http://dummy.restapiexample.com/api/v1/create", HttpMethod.POST, entity,
				String.class)).thenReturn(value);

		Long id = payRollService.createEmployee(emp);

		Assert.assertEquals(Long.valueOf(-1), Long.valueOf(id));
	}

	@Test
	public void testGetEmployees() {
		ReflectionTestUtils.setField(payRollService, "uri", "http://dummy.restapiexample.com/api/v1/");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		String body = "{\r\n" + "    \"status\": \"success\",\r\n" + "    \"data\": [\r\n" + "        {\r\n"
				+ "            \"id\": \"1\",\r\n" + "            \"employee_name\": \"Tiger Nixon\",\r\n"
				+ "            \"employee_salary\": \"320800\",\r\n" + "            \"employee_age\": \"61\",\r\n"
				+ "            \"profile_image\": \"\"\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"id\": \"2\",\r\n" + "            \"employee_name\": \"Garrett Winters\",\r\n"
				+ "            \"employee_salary\": \"170750\",\r\n" + "            \"employee_age\": \"63\",\r\n"
				+ "            \"profile_image\": \"\"\r\n" + "        }\r\n" + "    ]\r\n" + "}";
		ResponseEntity<String> value = Mockito.spy(new ResponseEntity<String>(body, null, HttpStatus.CREATED));

		Mockito.when(restTemplate.exchange("http://dummy.restapiexample.com/api/v1/" + Constants.PAYROLL.ALL_EMPLOYEES,
				HttpMethod.GET, entity, String.class)).thenReturn(value);

		List<EmployeePayRollSearchDTO> data = payRollService.getEmployees();
		Assert.assertEquals(2, data.size());

	}

}
