package com.c823.consorcio.service;

import com.c823.consorcio.dto.ReportBasicDto;
import com.c823.consorcio.dto.ReportDTO;
import com.c823.consorcio.entity.ReportEntity;

import java.util.List;
import java.util.Optional;

public interface IReportService {


    ReportDTO saveReport(ReportDTO reportDTO);

    List<ReportBasicDto> getListReports();
}

