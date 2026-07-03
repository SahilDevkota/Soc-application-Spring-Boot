package com.example.SOCApplication.DTO.ResponseDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//DTO used for returning access and refresh token after authentication
public class TokenResponseDTO {

    private String AccessToken;
    private String RefreshToken;
}
