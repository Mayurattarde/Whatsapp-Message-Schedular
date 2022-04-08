package com.springboot.restapi.schedular.entity;

public class Client {

	 public Client(int clientId, String clientName, String token) {
	        super();
	        this.client_id = clientId;
	        this.client_name = clientName;
	        this.auth_token = token;
	    }
	
	 public Client() {
		// TODO Auto-generated constructor stub
	}

	private int client_id;
	 private String client_name;
	 private String auth_token;
	 
	 
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	
	@Override
	public String toString() {
		return "Client [client_id=" + client_id + ", client_name=" + client_name + ", auth_token=" + auth_token + "]";
	}
	 
	 
}
