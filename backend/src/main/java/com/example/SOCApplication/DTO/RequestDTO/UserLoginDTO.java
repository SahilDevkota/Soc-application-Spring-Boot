package com.example.SOCApplication.DTO.RequestDTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//DTO used for transferring User's Login data between client and server
public class UserLoginDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;


}
