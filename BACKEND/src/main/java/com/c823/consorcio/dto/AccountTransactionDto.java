package com.c823.consorcio.dto;

import java.util.List;
import lombok.Data;

@Data
public class AccountTransactionDto {

  private Long accountId;
  private Double balance;
  private TransactionBasicDto transaction;


}
