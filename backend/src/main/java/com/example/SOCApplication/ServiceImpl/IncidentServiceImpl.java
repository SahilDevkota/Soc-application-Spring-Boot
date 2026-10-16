package com.example.SOCApplication.ServiceImpl;


import com.example.SOCApplication.DTO.RequestDTO.IncidentDTO;
import com.example.SOCApplication.DTO.RequestDTO.IncidentRequestDTO;
import com.example.SOCApplication.Entity.Incident;
import com.example.SOCApplication.Repository.IncidentRepository;
import com.example.SOCApplication.Service.IncidentService;
import com.example.SOCApplication.Mapper.IncidentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentServiceImpl implements IncidentService {

    private final IncidentMapper incidentMapper;
    private final IncidentRepository incidentRepository;


    @Override
    public ResponseEntity<String> addIncident(IncidentRequestDTO incidentRequestDTO) {
        Incident incident = incidentMapper.DTOtoIncident(incidentRequestDTO);
        incidentRepository.save(incident);
        return ResponseEntity.ok("Incident Added Successfully");
    }
}
