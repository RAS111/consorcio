package com.c823.consorcio.dto;

import com.c823.consorcio.enums.Issue;
import com.c823.consorcio.enums.Status;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ReportBasicDto {

  private Long id;
  private LocalDate creationDate;
  private Issue issue;
  private Status status;
  private Long userId;
  private String userName;

}
