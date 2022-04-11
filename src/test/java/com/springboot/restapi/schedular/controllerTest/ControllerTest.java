package com.springboot.restapi.schedular.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.entity.Request;
import com.springboot.restapi.schedular.entity.Response;
import com.springboot.restapi.schedular.service.AuthService;
import com.springboot.restapi.schedular.service.Messageservice;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	   @MockBean
	    Messageservice messageservice;
	    @MockBean
	    AuthService authService;

	    @Autowired
	    private MockMvc mvc;


	    public static ObjectMapper objectMapper = new ObjectMapper();

	    @Test
	    void authenticationTest() throws Exception {
	        Request request = new Request("test message", "7218893588", "2022-04-30T15:45:20");
	        String jsonString = objectMapper.writeValueAsString(request);
	        MvcResult result = mvc.perform(post("/schedule/message").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();
            System.out.println("result" + result.toString());

	        String actualResponseString = result.getResponse().getContentAsString();
	        
	        System.out.println("actualResponseString" + actualResponseString);
	        
	        Response response = objectMapper.readValue(actualResponseString, Response.class);
	        
	        System.out.println(response.toString()+ "test contoler");
	        //response code for authentication failed - 500
	        assertThat(response.getCode()).isEqualTo(500);
	    }
	    
	    @Test
	    void validationTestForMessage() throws Exception {
	        Client dummyclient = new Client(101, "User", "jnkjnkjnk");
	        when(authService.validateToken("jnkjnkjnk")).thenReturn(dummyclient);
	        
	        //empty message will be sent.........
	        Request request = new Request("Test message", "7218893588", "2022-04-30Invalid");
	        
	        String jsonString = objectMapper.writeValueAsString(request);
	        MvcResult result = mvc.perform(post("/schedule/messages").contentType(MediaType.APPLICATION_JSON_VALUE).header("token", "jnkjnkjnk").content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

	        String actualResponseString = result.getResponse().getContentAsString();
	        Response response = objectMapper.readValue(actualResponseString, Response.class);

	        //response code for non-valid request body - 406
	        //expected message = "message should not be empty"
	        assertThat(response.getCode()).isEqualTo(406);
	        assertThat(response.getMessage() == "message should not be empty");
	    }
	    
	    
	    

}
