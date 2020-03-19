package com.netent.employee.exception;

public class PayRollServiceException extends RuntimeException {

	private static final long serialVersionUID = 7560630281382014048L;

	public PayRollServiceException() {
		super();
	}

	public PayRollServiceException(String message) {
		super(message);
	}
}
