package com.c823.consorcio.controllerTest.Account;

import com.c823.consorcio.service.IAccountService;
import com.c823.consorcio.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUserService iUserService;
    @MockBean
    private IAccountService iAccountService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBalance() {
    }

    @Test
    void getAccountById() {
    }
}