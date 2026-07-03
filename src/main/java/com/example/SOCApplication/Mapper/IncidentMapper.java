package com.example.SOCApplication.Mapper;

import com.example.SOCApplication.DTO.RequestDTO.IncidentDTO;
import com.example.SOCApplication.DTO.RequestDTO.IncidentRequestDTO;
import com.example.SOCApplication.Entity.Incident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

//Mapper for converting between Incident entity and DTO
public interface IncidentMapper {

    IncidentRequestDTO incidentToDTO(Incident incident);
    Incident DTOtoIncident(IncidentRequestDTO IncidentRequestDTO);

}
