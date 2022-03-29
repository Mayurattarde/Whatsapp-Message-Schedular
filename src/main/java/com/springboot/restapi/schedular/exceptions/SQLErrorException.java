package com.springboot.restapi.schedular.exceptions;

public class SQLErrorException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private int errorCode;
	
	public SQLErrorException(String errorMessage, int errorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	
}