package com.c823.consorcio.controller.auth;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.c823.consorcio.auth.dto.AuthenticationRequest;
import com.c823.consorcio.auth.dto.AuthenticationResponse;
import com.c823.consorcio.auth.dto.ResponseUserDto;
import com.c823.consorcio.auth.service.UserDetailsCustomService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class UserAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDetailsCustomService userDetailsServices;
    @Autowired
    private ObjectMapper objectMapper;



    @BeforeEach
    void init() {
        /*
        ResponseUserDto userDto = new ResponseUserDto();
        userDto.setFirstName("Abel");
        userDto.setLastName("Acevedo");
        userDto.setEmail("abel@gmail.com");
        userDto.setFloor("1°B Flia Acevedo");
        userDto.setPassword("12345678");
        userDto.setRole(RoleName.USER);
        userDto.setApartmentNumber(25L);
        userDto.setCreationDate(new Date(100000));
*/
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

    @Test
    public void singIndTest() throws Exception {
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = new AuthenticationResponse();

        when(userDetailsServices.signIn(any(AuthenticationRequest.class))).thenReturn(response);
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt", is(response.getJwt())));


    }

    private AuthenticationRequest createAuthenticationRequest() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("abel@gmail.com");
        request.setPassword("12345678");
        return request;
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