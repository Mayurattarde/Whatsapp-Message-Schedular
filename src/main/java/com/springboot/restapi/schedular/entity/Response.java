package com.springboot.restapi.schedular.entity;

//import javax.xml.validation.*;
public class Response {

	private Integer code;
	private String message;
	
	 @Override
	    public String toString() {
	        return "Response{" +
	                "code=" + code +
	                ", Message='" + message + '\'' +
	                '}';
	    }
    	
	
	public Response(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public Response() {
		super();
	}


	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
