package com.springboot.restapi.schedular.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapi.schedular.dao.ClientDao;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;

@Service
public class AuthService {


    Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    ClientDao clientDao;


    public Client validateToken(String token) throws SQLErrorException {
        System.out.println("in validate token service");
        Client client = clientDao.getClientUsingToken(token);
        return client;
    }
}
