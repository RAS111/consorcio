package com.c823.consorcio.repository;

import com.c823.consorcio.entity.ReportEntity;
import com.c823.consorcio.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReportRepository extends JpaRepository< ReportEntity, Long> {
    List<ReportEntity> findAllByUser(UserEntity user);
}
