package com.springboot.restapi.schedular.entity;

//import java.time.LocalDate;
//import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;

//import org.springframework.format.annotation.DateTimeFormat;

import com.springboot.restapi.schedular.annotations.ContactNumberConstraint;
import com.springboot.restapi.schedular.annotations.CustomDateConstraint;

public class Request {

	@NotNull
	@NotEmpty(message = "Message should not be empty")
	private String message;
	
	public Request(String message, String phonenumber, String scheduledTime) {
        this.message = message;
        this.phonenumber = phonenumber;
        this.scheduledTime = scheduledTime;
    }
	
	@ContactNumberConstraint
	private String phonenumber;
	
    
	@CustomDateConstraint
	private String scheduledTime;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(String scheduledTime) {
		
		this.scheduledTime = scheduledTime;
	}
	
	@Override
	public String toString() {
		return "Request [message=" + message + ", phonenumber=" + phonenumber + ", scheduledTime=" + scheduledTime
				+ "]";
	}
}
