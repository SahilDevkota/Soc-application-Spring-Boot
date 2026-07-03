package com.example.SOCApplication.DTO.RequestDTO;

import com.example.SOCApplication.Enum.Severity;


//DTO used for transferring alert data between client and server
public class AlertDTO {

    private String name;

    private String source;

    private String message;

    private Severity severity;

}
