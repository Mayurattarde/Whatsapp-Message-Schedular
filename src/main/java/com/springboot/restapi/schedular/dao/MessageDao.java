package com.springboot.restapi.schedular.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.entity.Message;
import com.springboot.restapi.schedular.entity.Request;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;
import com.springboot.restapi.schedular.rowmappers.ClientMapper;
import com.springboot.restapi.schedular.rowmappers.MessageMapper;



@Component
public class MessageDao {

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
			throw new SQLErrorException("error while doing sql operation",406);
		}

	}

	public Client validateToken(String token)  {
		String query = "select * from client_details where auth_token= ?";
		Client client = null;
		try {
			client = jdbcTemplate.queryForObject(query,new ClientMapper(),new Object[] {token});
			return client;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	
	public List<Message> pollMessagesFromDatabase(){
		String query = "select * from message_details where pending_status = true and scheduled_at between now() and date_add(now(), interval 1 minute)";
//		String query = "select * from message where scheduled_at between \"2022-03-21T18:10:00\" and \"2022-03-21T18:11:00\"";

		List<Message> messages = null;
		System.out.println("In polling function...at "+LocalDateTime.now());
		try
		{
			messages = jdbcTemplate.query(query,new MessageMapper());
			return messages;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public int updateMessageStatus(Boolean pending_status,Boolean submited_status, String whatsAppMessageId,LocalDateTime submitted_at,Integer message_id){

		String query ="UPDATE message_details set pending_status = ?, submitted_status=?, submitted_at=?,whatsapp_api_message_id=? where message_id = ?";
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