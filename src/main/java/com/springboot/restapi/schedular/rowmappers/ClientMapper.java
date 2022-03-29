package com.springboot.restapi.schedular.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.springboot.restapi.schedular.entity.Client;
public class ClientMapper implements RowMapper<Client>{
	
	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Client client = new Client();
		client.setClient_id(rs.getInt("client_id"));
		client.setClient_name(rs.getString("client_name"));
		client.setAuth_token(rs.getString("auth_token"));
		return client;
	}
}