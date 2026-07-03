package com.example.SOCApplication.Service;

import com.example.SOCApplication.DTO.RequestDTO.UserLoginDTO;
import com.example.SOCApplication.DTO.RequestDTO.UserRegDTO;
import com.example.SOCApplication.DTO.ResponseDTO.TokenResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> registerUser(UserRegDTO userRegDTO);
    ResponseEntity<?> UserLogin(UserLoginDTO userLoginDTO);
}
