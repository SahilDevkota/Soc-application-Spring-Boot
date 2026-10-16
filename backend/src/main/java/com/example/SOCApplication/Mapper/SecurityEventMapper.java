package com.example.SOCApplication.Mapper;

import com.example.SOCApplication.DTO.RequestDTO.LogbackDTO;
import com.example.SOCApplication.Entity.SecurityEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SecurityEventMapper {

    @Mapping(source="username",target = "username")
    @Mapping(source="event",target = "event")
    @Mapping(source="failedAttempts",target = "failedAttempts")
    @Mapping(source="timestamp",target = "timestamp")
    LogbackDTO entityToDto(SecurityEvent securityEvent);

    @Mapping(source="username",target = "username")
    @Mapping(source="event",target = "event")
    @Mapping(source="failedAttempts",target = "failedAttempts")
    @Mapping(source="timestamp",target = "timestamp")
    SecurityEvent DTOtoEntity(LogbackDTO logbackDTO);
}
