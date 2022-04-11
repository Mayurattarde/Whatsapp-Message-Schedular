package com.springboot.restapi.schedular.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;
import com.springboot.restapi.schedular.rowmappers.ClientMapper;

@Repository
public class ClientDao {

	
	Logger logger = LoggerFactory.getLogger(ClientDao.class);
    
	
	 @Autowired
	    JdbcTemplate jdbcTemplate;
	 
	 public Client getClientUsingToken(String token) throws SQLErrorException {
	        String query = "select * from client_details where auth_token= ?";
	        Client client = null;
	        try {
	            client = jdbcTemplate.queryForObject(query, new ClientMapper(), token);
	            System.out.println("query--> " + query);
	            System.out.println("query result--> " + client.toString());
	            return client;
	        } catch (Exception e) {
	            logger.warn(e.getMessage());
	            //throw new SQLErrorException("sql error while validating client using token");
	            return null;
	        }

	    }

}
