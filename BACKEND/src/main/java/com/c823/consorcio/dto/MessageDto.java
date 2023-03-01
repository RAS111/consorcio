package com.c823.consorcio.dto;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Data
public class MessageDto {
  private Long id;
  private String subject;
  private String message;
  private String detail;
  private Long userId;
  private LocalDate creationDate;

}
