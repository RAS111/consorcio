package com.c823.consorcio.service.Impl;

import com.c823.consorcio.auth.exception.ParamNotFound;
import com.c823.consorcio.dto.ReservationBasicDto;
import com.c823.consorcio.dto.ReservationDto;
import com.c823.consorcio.entity.MessageEntity;
import com.c823.consorcio.entity.ReservationEntity;
import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.mapper.AmenitiesMap;
import com.c823.consorcio.repository.IReservationRepository;
import com.c823.consorcio.repository.IUserRepository;
import com.c823.consorcio.service.IAmenitiesService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AmenitiesServiceImpl implements IAmenitiesService {
  @Autowired
  private AmenitiesMap amenitiesMap;
  @Autowired
  private IReservationRepository iReservationRepository;
  @Autowired
  private IUserRepository userRepository;


  @Override
  public ReservationDto saveReservation(ReservationDto reservationDto) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserEntity user = userRepository.findByEmail(email);
    Long userId= user.getUserId();
    List<ReservationEntity> reservations = iReservationRepository.findAll();
    ReservationEntity entity = amenitiesMap.amenitieDto2Entity(reservationDto,userId);
    reservations.forEach(reservation -> {
      if(reservations.stream().anyMatch(i -> reservationDto.getReservationDate().equals(i.getReservationDate()) )
      && reservations.stream().anyMatch(i -> reservationDto.getTurn().equals(i.getTurn()))){
        throw new ParamNotFound("The day and turn already in use");
      }
    });
    ReservationEntity entitySaved = iReservationRepository.save(entity);
    ReservationDto result = amenitiesMap.amenitieEntity2Dto(entitySaved);

    return result;
  }

  @Override
  public List<ReservationBasicDto> getReservations() {
    return amenitiesMap.amenitieEntityList2DtoList(iReservationRepository.findAll());
  }

  @Override
  public List<ReservationBasicDto> getReservationsByUser() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserEntity user = userRepository.findByEmail(email);
    Long userId = user.getUserId();
    List<ReservationEntity> reservationEntityList = iReservationRepository.findAllByUser(user);
    List<ReservationBasicDto> reservationList = amenitiesMap.amenitieEntityList2DtoList(reservationEntityList);
    return reservationList;
  }

  @Override
  public ReservationDto getDetailsById(Long reservationId) {
    ReservationEntity reservation = iReservationRepository.findById(reservationId).orElseThrow(
        ()-> new ParamNotFound("ID do not exist"));
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserEntity user = userRepository.findByEmail(email);
    if(!Objects.equals(user.getUserId(),reservation.getUser().getUserId())){
      throw new ParamNotFound("the Reservation id don't below to user");
    }
    ReservationDto result = amenitiesMap.amenitieEntity2Dto(reservation);
    return result;
  }
}
