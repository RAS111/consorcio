package com.c823.consorcio.service;

import com.c823.consorcio.auth.dto.ResponseUserDto;
import com.c823.consorcio.dto.AccountBasicDto;
import java.util.List;

public interface IUserService {

  List<AccountBasicDto> getAccountsBalance();

  ResponseUserDto findById(Long userId);
}
