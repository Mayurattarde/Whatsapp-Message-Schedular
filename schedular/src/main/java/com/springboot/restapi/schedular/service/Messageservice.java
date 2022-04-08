package com.springboot.restapi.schedular.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapi.schedular.dao.MessageDao;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.entity.Message;
import com.springboot.restapi.schedular.entity.Request;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;

@Service
public class Messageservice {

	@Autowired
	MessageDao eDAO;


	public Messageservice(MessageDao eDAO) {
        this.eDAO = eDAO;
    }


    public int saveMessage(Request requestBody, Client client) throws SQLErrorException {
        return eDAO.save(requestBody, client);
    }

    public int updateMessageStatus(Boolean pending_status, Boolean submited_status, String whatsAppMessageId, LocalDateTime submitted_at, Integer message_id) throws SQLErrorException {

        return eDAO.updateMessageStatus(pending_status, submited_status, whatsAppMessageId, submitted_at, message_id);
    }


    public List<Message> pollMessagesFromDatabase() throws SQLErrorException {
        return eDAO.getAllMessagesInOneMinute();
    }


	public int save(@Valid Request requestBody, Client client) {
		// TODO Auto-generated method stub
		return 0;
	}
}
