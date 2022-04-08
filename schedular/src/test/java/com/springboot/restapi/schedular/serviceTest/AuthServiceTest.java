package com.springboot.restapi.schedular.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.restapi.schedular.dao.ClientDao;
import com.springboot.restapi.schedular.entity.Client;
import com.springboot.restapi.schedular.exceptions.SQLErrorException;
import com.springboot.restapi.schedular.service.AuthService;

@SpringBootTest
public class AuthServiceTest {


    @Autowired
    AuthService authService;


    @MockBean
    ClientDao clientDao;


    @Test
    void validateToken() throws SQLErrorException {
        Client testClient = new Client(10125, "dummy", "dummytoken");
        when(clientDao.getClientUsingToken("dummytoken")).thenReturn(testClient);
        assertThat(authService.validateToken("dummytoken")).isEqualTo(testClient);
    }


    @Test
    void validateTokenAsInvalid() throws SQLErrorException {
        when(clientDao.getClientUsingToken("Invalid Token")).thenReturn(null);
        assertThat(authService.validateToken("Invalid Token")).isNull();
    }
}
