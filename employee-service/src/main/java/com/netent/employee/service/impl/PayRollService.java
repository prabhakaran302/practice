package com.netent.employee.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.netent.employee.dto.EmployeeBulkDTO;
import com.netent.employee.dto.EmployeePayRollDTO;
import com.netent.employee.dto.EmployeePayRollSearchDTO;
import com.netent.employee.model.Employee;
import com.netent.employee.util.Constants;

@Service
public class PayRollService {
	@Value("${payroll.server.baseUri}")
	private String uri;

	@Autowired
	private RestTemplate restTemplate;

	public Long createEmployee(Employee emp) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Gson gson = new Gson();
		String json = gson.toJson(emp);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);

		ResponseEntity<String> response = restTemplate.exchange(uri + Constants.PAYROLL.CREATE, HttpMethod.POST, entity,
				String.class);
		EmployeePayRollDTO empPayRollDTO = new Gson().fromJson(response.getBody(), EmployeePayRollDTO.class);
		return (empPayRollDTO != null && empPayRollDTO.getData() != null) ? empPayRollDTO.getData().getId() : -1;
	}

	public List<EmployeePayRollSearchDTO> getEmployees() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri + Constants.PAYROLL.ALL_EMPLOYEES, HttpMethod.GET,
				entity, String.class);

		EmployeeBulkDTO data = new Gson().fromJson(result.getBody(), EmployeeBulkDTO.class);
		return data.getData();
	}
}
