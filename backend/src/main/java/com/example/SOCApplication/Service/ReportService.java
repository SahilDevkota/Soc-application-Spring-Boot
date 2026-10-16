package com.example.SOCApplication.Service;

import com.example.SOCApplication.Entity.Report;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    ResponseEntity<String> saveReport(Report report);
    ResponseEntity<Report> getReportByAnID(Integer id);
    ResponseEntity<List<Report>> getReportList();
}
