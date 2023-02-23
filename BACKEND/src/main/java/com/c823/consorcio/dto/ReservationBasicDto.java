package com.c823.consorcio.dto;

import com.c823.consorcio.enums.Amenities;
import com.c823.consorcio.enums.Turn;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ReservationBasicDto {
  private Long reservationId;
  private Amenities name;
  private LocalDate reservationDate;
  private Turn turn;

}
