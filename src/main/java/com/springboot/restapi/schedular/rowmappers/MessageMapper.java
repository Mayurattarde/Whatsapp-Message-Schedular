package com.springboot.restapi.schedular.rowmappers;


import org.springframework.jdbc.core.RowMapper;

import com.springboot.restapi.schedular.entity.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setMessage_id(rs.getInt("message_id"));
        message.setMessage(rs.getString("message"));
        message.setScheduled_at(rs.getString("scheduled_at"));
        message.setDestination_phone_number(rs.getString("destination_phone_number"));
        message.setClient_id(rs.getInt("client_id"));
        message.setCreated_at(rs.getString("created_at"));
        message.setPending_status(rs.getBoolean("pending_status"));
        message.setScheduled_status(rs.getBoolean("scheduled_status"));
        message.setSubmitted_at(rs.getString("submitted_at"));
        message.setSubmitted_status(rs.getBoolean("submitted_status"));
        message.setWhatsapp_api_message_id(rs.getString("whatsapp_api_message_id"));
        return message;
    }
}
