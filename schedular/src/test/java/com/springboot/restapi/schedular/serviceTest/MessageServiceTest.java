package com.springboot.restapi.schedular.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.restapi.schedular.dao.MessageDao;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.entity.Message;
import com.springboot.restapi.schedular.entity.Request;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;
import com.springboot.restapi.schedular.service.Messageservice;

@SpringBootTest
public class MessageServiceTest {


    @MockBean
    MessageDao messageDao;

    @Autowired
    Messageservice messageService;

    Logger logger = LoggerFactory.getLogger(MessageServiceTest.class);
    
    @Test
    void updateMessageStatus() throws SQLErrorException {
        when(messageDao.updateMessageStatus(any(), any(), any(), any(), any())).thenReturn(1);
        int actualResult = messageService.updateMessageStatus(any(), any(), any(), any(), any());
        assertEquals(1, actualResult);
    }


    @Test
    void saveMessage() throws SQLErrorException {
        Client dummyclient = new Client(101, "Mayur", "jnkjnkjnk");
        Request request = new Request("test message", "7972757302", "2022-04-06T09:42:00");
        when(messageDao.save(request, dummyclient)).thenReturn(1);
        int actualResult = messageService.saveMessage(request, dummyclient);
        assertThat(actualResult).isEqualTo(1);

    }
    private void assertEquals(int i, int actualResult) {
		// TODO Auto-generated method stub
		
	}
    
    @Test
    void pollMessagesFromDatabase() throws SQLErrorException {
        List<Message> messageList = Collections.emptyList();
        when(messageDao.getAllMessagesInOneMinute()).thenReturn(messageList);
        List<Message> actualList = messageService.pollMessagesFromDatabase();
        assertEquals(messageList.size(), actualList.size());
    }

    @Test
    void saveMessageAsNull() {
        String expectedMessage = "sql error while inserting message";
        String actualMessage = "";


        Client dummyclient = new Client(101, "dummy", "dummytoken");
        Request request = new Request("test message", "7972757302", "2022-04-30T15:45:20");
        try {
            when(messageDao.save(any(), any())).thenThrow(new SQLErrorException("sql error while inserting message"));
            int actualResult = messageService.saveMessage(null, null);
            assertEquals(1, actualResult);
        } catch (SQLErrorException e) {
            actualMessage = e.getMessage();

        }
        assertThat(actualMessage).isEqualTo(expectedMessage);

    }


    

	
  
}
