package com.c823.consorcio.controller;

import com.c823.consorcio.dto.AccountBasicDto;
import com.c823.consorcio.dto.AccountDto;
import com.c823.consorcio.service.IAccountService;
import com.c823.consorcio.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("https://live-to.sytes.net/")
public class AccountController {

  @Autowired
  private IUserService iUserService;
  @Autowired
  private IAccountService iAccountService;

  @GetMapping("/balance")
  public ResponseEntity<List<AccountBasicDto>> getBalance(){
    List<AccountBasicDto> accounts = iUserService.getAccountsBalance();
    return ResponseEntity.ok(accounts);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<AccountDto>> getAccountById(@PathVariable Long userId){
    List<AccountDto> listAccounts = this.iAccountService.finAllByUser(userId);
    return ResponseEntity.ok().body(listAccounts);
  }














}
