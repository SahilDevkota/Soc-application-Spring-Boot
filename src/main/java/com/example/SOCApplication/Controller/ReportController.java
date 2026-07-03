package com.example.SOCApplication.Controller;

import com.example.SOCApplication.Entity.Report;
import com.example.SOCApplication.ServiceImpl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("report")
@RequiredArgsConstructor

public class ReportController {


    //Service for handling Report operations
    private final ReportServiceImpl reportService;


    //Endpoint for saving the report
    @PostMapping("/save")
    public ResponseEntity<String> saveReport(@RequestBody Report report){
        return reportService.saveReport(report);
    }

    //Endpoint for grabbing the document with the required ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Integer id){
        return reportService.getReportByAnID(id);
    }

    //Endpoint for grabbing a list of report from the database
    @GetMapping("/list")
    public ResponseEntity<List<Report>> getListOfReport(){
        return  reportService.getReportList();
    }



}
