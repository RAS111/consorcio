package com.c823.consorcio.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MessageBasicDto {

  private String subject;
  private LocalDate creationDate;

}
