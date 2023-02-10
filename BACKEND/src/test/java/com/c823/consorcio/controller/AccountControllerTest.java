package com.c823.consorcio.controller;

import com.c823.consorcio.auth.service.AuthEntryPointJwt;
import com.c823.consorcio.dto.AccountDto;
import com.c823.consorcio.entity.AccountEntity;
import com.c823.consorcio.entity.RoleEntity;
import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.enums.RoleName;
import com.c823.consorcio.service.IAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthEntryPointJwt authEntryPointJwt;

    @MockBean
    private IAccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    private AccountDto accountDto;
    private AccountDto accountDto2;
    List<AccountEntity> accountEntities;

    private UserEntity user;
    @BeforeEach
    public void init(){
        user = new UserEntity();
        user.setUserId(1L);
        user.setRole(new RoleEntity(1L,RoleName.USER,List.of()));

        accountEntities = new ArrayList<>(createAccounts());
        user.setAccounts(accountEntities);

        accountDto = new AccountDto();
        accountDto.setAccountId(1L);
        accountDto.setBalance(100.00);
        accountDto.setCreationDate(new Date(10000));
        accountDto.setUpdateDate(new Date(20000));

        accountDto2 = new AccountDto();
        accountDto2.setAccountId(2L);
        accountDto2.setBalance(200.00);
        accountDto2.setCreationDate(new Date(40000));
        accountDto2.setUpdateDate(new Date(50000));


    }

    private List<AccountEntity> createAccounts() {
        List<AccountEntity> collection = new ArrayList<>();

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        accountEntity.setBalance(100.00);
        accountEntity.setCreationDate(new Date(10000));
        accountEntity.setUpdateDate(new Date(20000));
        collection.add(accountEntity);

        AccountEntity accountEntity2 = new AccountEntity();
        accountEntity2.setAccountId(2L);
        accountEntity2.setBalance(200.00);
        accountEntity2.setCreationDate(new Date(40000));
        accountEntity2.setUpdateDate(new Date(50000));

        collection.add(accountEntity2);
        return collection;
    }

    @Test
    public void getAccountByIdUserTest() throws Exception {
        List<AccountDto> accountDtos = new ArrayList<>();
        accountDtos.addAll(dtoToentityList(user.getAccounts()));

        when(accountService.finAllByUser(anyLong())).thenReturn(accountDtos);

        mockMvc.perform(get("/accounts/{userId}", 1L)
                        .contentType(objectMapper.writeValueAsString(accountDtos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountDtos.get(0).accountId", is(1)));

                //.andExpect(jsonPath("$.data.id", is(1)))






    }

    private List<AccountDto> dtoToentityList(List<AccountEntity> accounts) {
        List<AccountDto> accountDtoList = new ArrayList<>();
        for(AccountEntity account: accounts){
            accountDtoList.add(dtoRoEntity(account));
        }
        return accountDtoList;
    }

    private AccountDto dtoRoEntity(AccountEntity account) {
        return AccountDto.builder()
                .accountId(account.getAccountId())
                .creationDate(account.getCreationDate())
                .balance(account.getBalance())
                .updateDate(account.getUpdateDate())
                .build();
    }


}
