package com.c823.consorcio.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MessageBasicDto {
  private Long userId;
  private String userName;
  private String subject;
  private String message;
  private LocalDate creationDate;

}
