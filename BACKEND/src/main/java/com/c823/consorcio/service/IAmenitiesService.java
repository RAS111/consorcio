package com.c823.consorcio.service;

import com.c823.consorcio.dto.ReservationBasicDto;
import com.c823.consorcio.dto.ReservationDto;
import java.util.List;

public interface IAmenitiesService {

  ReservationDto saveReservation(ReservationDto reservationDto);

  List<ReservationBasicDto> getReservations();

  List<ReservationBasicDto> getReservationsByUser();

  ReservationDto getDetailsById(Long reservationId);
}
