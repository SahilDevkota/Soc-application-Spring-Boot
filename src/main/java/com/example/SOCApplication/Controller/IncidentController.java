package com.example.SOCApplication.Controller;


import com.example.SOCApplication.DTO.RequestDTO.IncidentDTO;
import com.example.SOCApplication.DTO.RequestDTO.IncidentRequestDTO;
import com.example.SOCApplication.ServiceImpl.IncidentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incident")
public class IncidentController {

    //Service for handling Incident Operations

    private final IncidentServiceImpl incidentService;

    //Endpoint for adding Incident
    @PostMapping("/add")
    public ResponseEntity<String> addIncident(@RequestBody IncidentRequestDTO incidentRequestDTO){
        return incidentService.addIncident(incidentRequestDTO);
    }
}
