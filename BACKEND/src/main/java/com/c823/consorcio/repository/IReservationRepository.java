package com.c823.consorcio.repository;

import com.c823.consorcio.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<ReservationEntity,Long > {

}
