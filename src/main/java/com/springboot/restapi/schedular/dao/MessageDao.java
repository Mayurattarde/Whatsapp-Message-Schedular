package com.springboot.restapi.schedular.dao;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.entity.Message;
import com.springboot.restapi.schedular.entity.Request;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;
import com.springboot.restapi.schedular.rowmappers.MessageMapper;




@Component
public class MessageDao {
	Logger logger = LoggerFactory.getLogger(MessageDao.class);
	@Autowired
	JdbcTemplate jdbcTemplate;



	public void welcome(){
		System.out.println("welcome to the dao.");
	}


	public int save(Request requestBody,Client client) throws SQLErrorException  {
		int result = 0;
		String query = "insert into message_details(message,scheduled_at,destination_phone_number,client_id,created_at,pending_status,scheduled_status) values (?,?,?,?,?,?,?)";
		try {
			result = jdbcTemplate.update(query, requestBody.getMessage(), requestBody.getScheduledTime(),
					requestBody.getPhonenumber(), client.getClient_id(), LocalDateTime.now(), true,true);
			return result;
		} catch (Exception e) {
			throw new SQLErrorException("error while doing sql operation");
		}

	}

	 public List<Message> getAllMessagesInOneMinute() throws SQLErrorException {
//       String query = "select * from message where pending_status = true and scheduled_at between now() and date_add(now(), interval 1 minute)";
       String query = "select * from message_details where pending_status = true and scheduled_at < now()";

       List<Message> messages = Collections.emptyList();
       logger.info("polling messages at " + LocalDateTime.now());
       try {
           messages = jdbcTemplate.query(query, new MessageMapper());
           return messages;
       } catch (Exception e) {
           logger.warn(e.getMessage());
           throw new SQLErrorException("error while doing polling messages from DB");
       }
   }
	

	public int updateMessageStatus(Boolean pending_status,Boolean submited_status, String whatsAppMessageId,LocalDateTime submitted_at,Integer message_id){

		String query ="UPDATE message_details set pending_status = ?, submitted_status=?, submitted_at=?,whatsapp_api_message_id=? where message_id = ?";
		System.out.println("updating message status for messageId " + message_id);
		int result = 0;
		try
		{
			result = jdbcTemplate.update(query,pending_status,submited_status,submitted_at,whatsAppMessageId,message_id);
			return result;
		}catch (Exception e){
			return 0;
		}
		
		

	}




	
	
	
	
	

}