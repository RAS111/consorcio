package com.c823.consorcio.mapper;

import com.c823.consorcio.dto.ReservationBasicDto;
import com.c823.consorcio.dto.ReservationDto;
import com.c823.consorcio.entity.ReservationEntity;
import com.c823.consorcio.repository.IUserRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmenitiesMap {
  @Autowired
  private IUserRepository iUserRepository;

  public ReservationEntity amenitieDto2Entity(ReservationDto reservationDto, Long userId) {
    ReservationEntity entity = new ReservationEntity();
    entity.setName(reservationDto.getName());
    entity.setTurn(reservationDto.getTurn());
    entity.setReservationDate(reservationDto.getReservationDate());
    entity.setUser(iUserRepository.findByUserId(userId));
    return entity;
  }

  public ReservationDto amenitieEntity2Dto(ReservationEntity entitySaved) {
    ReservationDto dto = new ReservationDto();
    dto.setReservationId(entitySaved.getReservationId());
    dto.setName(entitySaved.getName());
    dto.setTurn(entitySaved.getTurn());
    dto.setReservationDate(entitySaved.getReservationDate());
    //Long id = entitySaved.getUserEntity().getUserId();
    dto.setUserId(entitySaved.getUser().getUserId());
    return dto;

  }

  private LocalDate stringToLocalDate(String stringDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    LocalDate date = LocalDate.parse(stringDate, formatter);
    return date;
  }

  public List<ReservationBasicDto> amenitieEntityList2DtoList(List<ReservationEntity> entities) {
    List<ReservationBasicDto> dtos = new ArrayList<>();
    for (ReservationEntity entity : entities){
      dtos.add(amenitieEntity2DtoBasic(entity));
    }
    return dtos;
  }

  private ReservationBasicDto amenitieEntity2DtoBasic(ReservationEntity entitySaved) {
    ReservationBasicDto dto = new ReservationBasicDto();
    dto.setReservationId(entitySaved.getReservationId());
    dto.setName(entitySaved.getName());
    dto.setTurn(entitySaved.getTurn());
    dto.setReservationDate(entitySaved.getReservationDate());
    dto.setUserName(entitySaved.getUser().getFirstName());
    dto.setUserId(entitySaved.getUser().getUserId());
    return dto;
  }
}
