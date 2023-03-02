package com.c823.consorcio.service;

import com.c823.consorcio.dto.ReportBasicDto;
import com.c823.consorcio.dto.ReportDTO;


import java.util.List;

public interface IReportService {


    ReportDTO saveReport(ReportDTO reportDTO);

    List<ReportBasicDto> getListReports();

    List<ReportBasicDto> getReportsByUser();
}

