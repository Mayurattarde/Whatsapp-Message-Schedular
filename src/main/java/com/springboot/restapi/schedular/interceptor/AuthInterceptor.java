package com.springboot.restapi.schedular.interceptor;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/*import org.springframework.boot.web.server.Cookie;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;*/

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.entity.Response;
import com.springboot.restapi.schedular.service.AuthService;
//import com.springboot.restapi.schedular.entity.Message;
//import com.springboot.restapi.schedular.entity.Response;

@Component
public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	AuthService authService;
	@Override
	
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {
	       System.out.println("In prehandler interceptor");
	        String token = request.getHeader("token");
	        System.out.println("token as header " + token);
	        Client client = authService.validateToken(token);
	        if (client == null) {
	        	 System.out.println("client is NULL.....authentication failed.");
	            response.setContentType("application/json");
	            response.setStatus(400);
	            PrintWriter out = response.getWriter();
	            Response resp = new Response(500, "Authentication failed");
	            String responseString = new ObjectMapper().writeValueAsString(resp);
	            out.print(responseString);
	            return false;
	        }
	        System.out.println("client here in interceptor " + client);
	        request.setAttribute("client", client);
	        return true;
	    }
	

}
