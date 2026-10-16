package com.example.SOCApplication.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LogbackDTO {


    private String username;
    private String event;
    private Integer failedAttempts;
    private LocalDateTime timestamp;
}
