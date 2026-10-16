package com.example.SOCApplication.DTO.RequestDTO;

import com.example.SOCApplication.Enum.Severity;
import com.example.SOCApplication.Enum.Status;

//DTO used for transferring Incident data between client and server
public class IncidentDTO {

    private String name;

    private String description;

    private Severity severity;

    private Status status;

}
