package com.springboot.restapi.schedular.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.schedular.dao.MessageDao;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.entity.Request;
import com.springboot.restapi.schedular.entity.Response;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;

@RestController
@RequestMapping("/schedule/")
public class MessageController {
	
	@Autowired
    MessageDao eDAO;

	@PostMapping("/messages")
	public Response MessageHandler(@RequestBody @Valid Request requestBody, HttpServletRequest request) {
		Response response = null;
		
		String request_id =  UUID.randomUUID().toString();
        System.out.println("requestID is -->  "+ request_id);

		
		try {
			Client client =   (Client) request.getAttribute("client");
			
			//if client is null...authentication is failed
            if(client == null){
                response = new Response(request_id, 500, "Authentication failed..");
                return response;
            }
			
			System.out.println("client here  "+ client.toString());
			int result = eDAO.save(requestBody,client);
			
				response = new Response(request_id, 200, "Message is scheduled successfully");
			
		} catch (SQLErrorException e1) {
			response = new Response(request_id, e1.getErrorCode(), e1.getErrorMessage());
		}
		catch(Exception e1) {
			response = new Response(request_id, 405, "something went wrong!!");
			
		}
		return response;
	}
				
				//eDAO.save(e)+" Message(s) saved successfully";
	@ExceptionHandler(MethodArgumentNotValidException.class)
	Response onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String request_id =  UUID.randomUUID().toString();
		/*
		 * System.out.println(e); System.out.println(e.getBindingResult().toString());
		 * System.out.println(e.getBindingResult().getFieldErrors().toString());
		 */
		FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
		String errorMessage =  fieldError.getDefaultMessage();
//		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
//			System.out.println("here... " + fieldError.getField() + " " + fieldError.getDefaultMessage());
//		}
		Response response = new Response(request_id,406,errorMessage);
		return response;
	}
	
	
	
	

}
