package com.springboot.restapi.schedular.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/*import org.springframework.boot.web.server.Cookie;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;*/

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.springboot.restapi.schedular.dao.MessageDao;
import com.springboot.restapi.schedular.entity.Client;
//import com.springboot.restapi.schedular.entity.Message;
//import com.springboot.restapi.schedular.entity.Response;

@Component
public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	MessageDao eDAO;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//return HandlerInterceptor.super.preHandle(request, response, handler);
		System.out.println("Prehandle called");
		String token = request.getHeader("token");
		
		Client client = eDAO.validateToken(token);
		if (client == null) {
			response.setContentType("application/json");
			//javax.servlet.http.Cookie ck = new javax.servlet.http.Cookie("message","first cookie");
			//response.addCookie(ck);
			//response.setStatus(400);
//			PrintWriter out = response.getWriter();
//			Response resp = new Response("random requestID", 1001, "Authentication failed");
//			String responseString = new Gson().toJson(resp);
//			out.print(responseString);
			request.setAttribute("client",null);
			return false;
		}
		System.out.println("client here..." + client.toString());
		request.setAttribute("client", client);
		
		return true;
		
	}
	

}
