package com.c823.consorcio.dto;

import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.enums.Issue;
import com.c823.consorcio.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ReportDTO {

    private LocalDate creationDate;
    private Issue issue;
    private Status status;
    private String detail;
    private String place;
    private Long userId;
    private String userName;

}
