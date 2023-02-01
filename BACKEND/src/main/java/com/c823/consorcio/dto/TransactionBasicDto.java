package com.c823.consorcio.dto;

import com.c823.consorcio.enums.TypeTransaction;
import lombok.Data;

@Data
public class TransactionBasicDto {

  private Long transactionId;
  private Double amount;
  private TypeTransaction type;

}
