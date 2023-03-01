package com.c823.consorcio.repository;


import com.c823.consorcio.entity.ReservationEntity;
import com.c823.consorcio.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservationRepository extends JpaRepository<ReservationEntity,Long > {
    List<ReservationEntity> findAllByUser(UserEntity user);
}
