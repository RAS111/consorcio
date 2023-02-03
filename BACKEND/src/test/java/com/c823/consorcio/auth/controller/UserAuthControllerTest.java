package com.c823.consorcio.auth.controller;


import static java.io.FileDescriptor.in;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.c823.consorcio.auth.dto.ResponseUserDto;
import com.c823.consorcio.auth.service.AuthEntryPointJwt;
import com.c823.consorcio.auth.service.JwtUtils;
import com.c823.consorcio.auth.service.UserDetailsCustomService;

import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.mapper.AccountMap;
import com.c823.consorcio.mapper.UserMap;
import com.c823.consorcio.repository.IUserRepository;
import com.c823.consorcio.service.IAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;


@SpringBootTest
@AutoConfigureMockMvc
class UserAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDetailsCustomService userDetailsServices;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserRepository userRepository;

    @BeforeEach
    void init() {

    }

    @Test
    public void singUpCorrect() throws Exception {
        ResponseUserDto userDto = createUserAuthDto();

        when(userDetailsServices.save(any(ResponseUserDto.class))).thenReturn(userDto);

        this.mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.email", is(userDto.getEmail())))
                    .andExpect(jsonPath("$.firstName", is(userDto.getFirstName())))
                    .andExpect(jsonPath("$.lastName", is(userDto.getLastName())))
                            .andExpect(jsonPath("$.floor", is(userDto.getFloor())));
                Assertions.assertEquals(90L,userDto.getApartmentNumber());


    }
    

    private ResponseUserDto createUserAuthDto() {
        return ResponseUserDto.builder()
                .email("abel@gmail.com")
                .firstName("Abel")
                .lastName("Acevedo")
                .password("12345678")
                .apartmentNumber(90L)
                .floor("6°B Flia Gonzales")
                .build();
    }

}