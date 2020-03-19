package com.netent.employee.util;

public class Constants {
	public interface Api {
		String ADD = "/add";
		String ADD_BULK = "/addall";
		String SEARCH = "/search/name/{name}/age/{age}";
	}

	public interface PAYROLL {
		String CREATE = "create";
		String ALL_EMPLOYEES = "employees";
	}
}
