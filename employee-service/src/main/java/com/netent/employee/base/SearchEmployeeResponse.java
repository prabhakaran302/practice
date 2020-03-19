package com.netent.employee.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.netent.employee.model.Employee;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchEmployeeResponse {
	private String message;
	private List<Employee> empList = new ArrayList<>();
	private HttpStatus status;
}
