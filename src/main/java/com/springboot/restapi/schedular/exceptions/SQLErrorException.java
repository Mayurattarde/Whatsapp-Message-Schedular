package com.springboot.restapi.schedular.exceptions;

public class SQLErrorException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private final String errorMessage;
	private final int errorCode=407;
	
	public SQLErrorException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	
}