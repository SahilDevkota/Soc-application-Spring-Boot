package com.example.SOCApplication.Service;


import com.example.SOCApplication.DTO.RequestDTO.IncidentDTO;
import com.example.SOCApplication.DTO.RequestDTO.IncidentRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface IncidentService {

    ResponseEntity<String> addIncident(IncidentRequestDTO incidentRequestDTO);
}
