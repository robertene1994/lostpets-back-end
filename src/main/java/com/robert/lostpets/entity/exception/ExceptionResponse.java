package com.robert.lostpets.entity.exception;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = -6573985284535012219L;

	private String field;
	private String code;
	private String exception;
	private String message;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
