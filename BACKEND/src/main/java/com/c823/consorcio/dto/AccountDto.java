package com.c823.consorcio.dto;

import com.c823.consorcio.entity.ApartmentEntity;
import com.c823.consorcio.entity.UserEntity;
import java.util.Date;
import java.util.List;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

  private Long accountId;
  private double balance;

  private Date creationDate;

  private Date updateDate;
  private UserEntity user;
  private ApartmentEntity apartment;


}
