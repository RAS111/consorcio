package com.c823.consorcio.controller;

import com.c823.consorcio.dto.ReportBasicDto;
import com.c823.consorcio.dto.ReservationBasicDto;
import com.c823.consorcio.service.IReportService;
import com.c823.consorcio.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/reports")

public class ReportController {
    @Autowired
    private IReportService reportService;

    @PostMapping("/create")
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO){
        ReportDTO report = this.reportService.saveReport(reportDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReportBasicDto>> getReports(){
        List<ReportBasicDto> listReports = this.reportService.getListReports();
        return ResponseEntity.ok().body(listReports);
    }

    @GetMapping("")
    public ResponseEntity<List<ReportBasicDto>> listReservationsByUser(){
        List<ReportBasicDto> list = this.reportService.getReportsByUser();
        return ResponseEntity.ok().body(list);
    }

    // TODO : Resta lista de repotes de todos los usuarios (GET/REPORTS) (ADMIN)
    // TODO: resta lista de reportes del usuario (GET/REPORTS/USER_ID) (ADMIN)




}
