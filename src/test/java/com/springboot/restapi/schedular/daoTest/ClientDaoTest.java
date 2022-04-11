package com.springboot.restapi.schedular.daoTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.restapi.schedular.dao.ClientDao;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;

@SpringBootTest
public class ClientDaoTest {

	 @Autowired
	    ClientDao clientDao;


	    Logger logger = LoggerFactory.getLogger(ClientDaoTest.class);

		
		 @Test void getClientUsingToken() throws SQLErrorException
		 {
			 String token = "jnkjnkjnk";
			 Client expectedClient = new Client(101, "User", "jnkjnkjnk");
		  Client actualResult = clientDao.getClientUsingToken(token);
		  assertThat(actualResult.toString()).isEqualTo(expectedClient.toString()); }
		 


	    @Test
	    void getClientUsingTokenInvalid() {
	        Client actualResult = null;
	        try {
	            actualResult = clientDao.getClientUsingToken("Invalid token");
	            System.out.println("actualresult " + actualResult);
	        } catch (SQLErrorException e) {
	            logger.info(e.getMessage());
	            assertThat(actualResult).isEqualTo(null);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
