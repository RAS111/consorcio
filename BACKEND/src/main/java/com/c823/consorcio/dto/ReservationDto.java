package com.c823.consorcio.dto;

import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.enums.Amenities;
import com.c823.consorcio.enums.Turn;
import java.time.LocalDate;
import java.util.Date;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReservationDto {

  private Long reservationId;
  private Amenities name;
  @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message="Formato de fecha inválido")
  private LocalDate reservationDate;
  private Turn turn;
  private Long userId;
}
