package com.example.SOCApplication.Controller;

import com.example.SOCApplication.DTO.RequestDTO.UserLoginDTO;
import com.example.SOCApplication.DTO.RequestDTO.UserRegDTO;
import com.example.SOCApplication.DTO.ResponseDTO.TokenResponseDTO;
import com.example.SOCApplication.Entity.User;
import com.example.SOCApplication.Repository.UserRepository;
import com.example.SOCApplication.ServiceImpl.RefreshTokenServiceImpl;
import com.example.SOCApplication.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class
UserController {



    //Service responsible for user operations
    private final UserServiceImpl userService;

    //Handles database operations related to user
    private final UserRepository userRepository;

    //Service responsible for handling Refresh Token operations
    private final RefreshTokenServiceImpl refreshTokenService;

    //Endpoint for registering user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegDTO userRegDTO){
        System.out.println("Working");
        return userService.registerUser(userRegDTO);
    }

    //Endpoint for logging in user
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        return userService.UserLogin(userLoginDTO);
    }

    //Endpoint for Getting a list of user
    //Only roles with FULL_CONTROL permission can access this endpoint
    //@PreAuthorize("hasAuthority('FULL_CONTROL')")
    @GetMapping("/all")
    public List<User> getAll(){
        System.out.println("Pre auth working");
        return userRepository.findAll();
    }

    //Endpoint for generating new access token
    @PostMapping("/refresh")
    public ResponseEntity<Map> refresh(@CookieValue("refreshtoken") String refreshToken){
       return refreshTokenService.RefreshAccessToken(refreshToken);

    }



}
