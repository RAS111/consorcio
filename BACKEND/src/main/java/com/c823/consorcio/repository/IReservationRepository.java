package com.c823.consorcio.repository;

import com.c823.consorcio.entity.ReservationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAmenitieRepository extends JpaRepository<ReservationEntity,Long > {

}
