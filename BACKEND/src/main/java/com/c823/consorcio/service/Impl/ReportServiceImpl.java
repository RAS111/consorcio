package com.c823.consorcio.service.Impl;

import com.c823.consorcio.dto.ReportBasicDto;
import com.c823.consorcio.dto.ReportDTO;
import com.c823.consorcio.entity.ReportEntity;
import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.mapper.ReportMap;
import com.c823.consorcio.repository.IReportRepository;
import com.c823.consorcio.repository.IUserRepository;
import com.c823.consorcio.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {
    @Autowired
     private IReportRepository reportRepository;
    @Autowired
    private ReportMap reportMap;
    @Autowired
    private IUserRepository userRepository;


  @Override
  public ReportDTO saveReport(ReportDTO reportDTO) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserEntity user = userRepository.findByEmail(email);
    Long userId = user.getUserId();
    ReportEntity reportEntity = reportMap.reportDto2Entity(reportDTO,userId);
    ReportEntity entitySaved = reportRepository.save(reportEntity);
    ReportDTO result = reportMap.reportEntity2Dto(entitySaved);

    return result;
  }

  @Override
  public List<ReportBasicDto> getListReports() {



    return reportMap.reportEntityList2DtoBasicList(reportRepository.findAll());
  }

  @Override
  public List<ReportBasicDto> getReportsByUser() {
      String email = SecurityContextHolder.getContext().getAuthentication().getName();
      UserEntity user = userRepository.findByEmail(email);
      List<ReportEntity> reportEntityList = reportRepository.findAllByUser(user);
      List<ReportBasicDto> reportList = reportMap.reportEntityList2DtoBasicList(reportEntityList);
      return reportList;
  }


}
