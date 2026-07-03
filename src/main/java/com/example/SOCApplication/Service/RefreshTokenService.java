package com.example.SOCApplication.Service;

import org.springframework.http.ResponseEntity;

public interface RefreshTokenService {

    ResponseEntity<?> RefreshAccessToken(String refreshToken);

}
