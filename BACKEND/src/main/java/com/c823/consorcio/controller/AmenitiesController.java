package com.c823.consorcio.controller;

import com.c823.consorcio.dto.ReservationBasicDto;
import com.c823.consorcio.dto.ReservationDto;
import com.c823.consorcio.service.IAmenitiesService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/reservations")


public class AmenitiesController {
  @Autowired
  private IAmenitiesService amenitiesService;

  @PostMapping("/amenities")
  public ResponseEntity<ReservationDto> reservation(@RequestBody ReservationDto reservationDto){
    ReservationDto reservation = this.amenitiesService.saveReservation(reservationDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
  }
  @GetMapping("/list")
  public ResponseEntity<List<ReservationBasicDto>> listReservations(){
    List<ReservationBasicDto> list = this.amenitiesService.getReservations();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("")
  public ResponseEntity<List<ReservationBasicDto>> listReservationsByUser(){
    List<ReservationBasicDto> list = this.amenitiesService.getReservationsByUser();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{reservationId}")
  public ResponseEntity<ReservationDto> getDetailsReservation(@PathVariable Long reservationId){
    ReservationDto reservation = this.amenitiesService.getDetailsById(reservationId);
    return ResponseEntity.ok().body(reservation);
  }
  // TODO : Resta lista de reservas del usuario autenticado (GET/RESERVATIONS)
  // TODO: resta lista de reservas del usuario visto desde el administrador (GET/RESERVATIONS/USER_ID)


}
