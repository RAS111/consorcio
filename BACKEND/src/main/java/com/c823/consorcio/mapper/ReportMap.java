package com.c823.consorcio.mapper;

import com.c823.consorcio.dto.ReportBasicDto;
import com.c823.consorcio.dto.ReportDTO;
import com.c823.consorcio.entity.ReportEntity;
import com.c823.consorcio.enums.Status;
import com.c823.consorcio.repository.IUserRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportMap {
  @Autowired
  private IUserRepository userRepository;


  public ReportEntity reportDto2Entity(ReportDTO reportDTO, Long userId) {
    ReportEntity entity = new ReportEntity();
    entity.setUser(userRepository.findByUserId(userId));
    entity.setIssue(reportDTO.getIssue());
    entity.setDetail(reportDTO.getDetail());
    entity.setPlace(reportDTO.getPlace());
    entity.setStatus(Status.ON_REVIEW);
    entity.setCreationDate(reportDTO.getCreationDate());
    return entity;
  }

  public ReportDTO reportEntity2Dto(ReportEntity entitySaved) {
    ReportDTO dto =  new ReportDTO();
    dto.setUserId(entitySaved.getUser().getUserId());
    dto.setUserName(entitySaved.getUser().getFirstName());
    dto.setIssue(entitySaved.getIssue());
    dto.setDetail(entitySaved.getDetail());
    dto.setPlace(entitySaved.getPlace());
    dto.setStatus(entitySaved.getStatus());
    dto.setCreationDate(entitySaved.getCreationDate());
    return dto;
  }

  public List<ReportBasicDto> reportEntityList2DtoBasicList(List<ReportEntity> entities) {
    List<ReportBasicDto> dtos = new ArrayList<>();
    for (ReportEntity entity : entities){
      dtos.add(reportEntity2BasicDto(entity));
    }
    return dtos;
  }

  private ReportBasicDto reportEntity2BasicDto(ReportEntity entity) {
    ReportBasicDto dto = new ReportBasicDto();
    dto.setId(entity.getReportId());
    dto.setCreationDate(entity.getCreationDate());
    dto.setIssue(entity.getIssue());
    dto.setStatus(entity.getStatus());
    dto.setUserId(entity.getUser().getUserId());
    dto.setUserName(entity.getUser().getFirstName());
    return dto;
  }
}
