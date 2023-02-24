package com.c823.consorcio.auth;

import com.c823.consorcio.auth.dto.AuthenticationRequest;
import com.c823.consorcio.auth.dto.AuthenticationResponse;
import com.c823.consorcio.auth.dto.ResponseUserDto;
import com.c823.consorcio.auth.dto.UserAuthDto;
import com.c823.consorcio.auth.service.JwtUtils;
import com.c823.consorcio.auth.service.UserDetailsCustomService;
import com.c823.consorcio.service.IUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
//@CrossOrigin("https://live-to.sytes.net")


public class UserAuthController {

  @Autowired
  private UserDetailsCustomService userDetailsServices;





  @PostMapping("/register")
  public ResponseEntity<ResponseUserDto> signUp(@Valid @RequestBody ResponseUserDto user) {
    ResponseUserDto userRegister = this.userDetailsServices.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userRegister);
  }

  //Todo terminar este endpoint auth/registerAdmin, crea el admin
  @PostMapping("/registerAdmin")
  public ResponseEntity<AuthenticationResponse> signUpAdmin(@Valid @RequestBody UserAuthDto user) {
    this.userDetailsServices.saveAdmin(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authenticationRequest) {

    AuthenticationResponse response = userDetailsServices.signIn(authenticationRequest);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


  }

