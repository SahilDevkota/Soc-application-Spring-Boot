package com.example.SOCApplication.ServiceImpl;

import com.example.SOCApplication.Entity.Report;
import com.example.SOCApplication.Repository.ReportRepository;
import com.example.SOCApplication.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public ResponseEntity<String> saveReport(Report report) {
        reportRepository.save(report);
        return ResponseEntity.ok("Report Saved Successfully");
    }

    @Override
    public ResponseEntity<Report> getReportByAnID(Integer id) {
        if(reportRepository.existsById(id)){
            Report report = reportRepository.findById(id).orElseThrow(()->new RuntimeException("Report Not found!"));
            return ResponseEntity.ok(report);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Report>> getReportList() {
        List<Report> reportList = reportRepository.findAll();
        return ResponseEntity.ok(reportList);
    }
}
