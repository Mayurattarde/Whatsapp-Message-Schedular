package com.springboot.restapi.schedular.entity;

//import javax.xml.validation.*;
public class Response {

	private String request_id;
	private Integer code;
	private String message;
	
    	
	
	public Response(String request_id, Integer code, String message) {
		super();
		this.request_id = request_id;
		this.code = code;
		this.message = message;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public Integer getCode() {
		return code;
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
