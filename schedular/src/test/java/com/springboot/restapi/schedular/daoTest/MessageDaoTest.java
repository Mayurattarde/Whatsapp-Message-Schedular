package com.springboot.restapi.schedular.daoTest;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.restapi.schedular.dao.MessageDao;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.entity.Request;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;

@SpringBootTest
public class MessageDaoTest {


    @Autowired
    MessageDao messageDao;

    Logger logger = LoggerFactory.getLogger(MessageDaoTest.class);

    @Test
    void insertMessage() throws SQLErrorException {
        Client dummyclient = new Client(101,"dummy","dummytoken");
        Request request = new Request("dummy message for testing purpose", "7972757302", "2022-03-30T18:05:20");
        int actualResult = messageDao.save(request,dummyclient);
        assertEquals(1,actualResult);
    }
    
    private void assertEquals(int i, int actualResult) {
	
		
	}

	@Test
    void updateMessageStatus() throws SQLErrorException {
       int actualResult =  messageDao.updateMessageStatus(false,true,"dummy_whatsapp_Id", LocalDateTime.now(),23);
       assertEquals(1,actualResult);
    }
}
