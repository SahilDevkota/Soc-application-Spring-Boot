package com.example.SOCApplication.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//DTO used for transferring User's Response data between client and server
public class UserRespDTO {

    private String username;

    private String email;

    private String role;
}
