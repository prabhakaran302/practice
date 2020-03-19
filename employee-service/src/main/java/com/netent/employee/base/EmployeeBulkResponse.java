package com.netent.employee.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeBulkResponse extends ResponseModel {
	private int totalSaved;
	private int totalFailed;
}
