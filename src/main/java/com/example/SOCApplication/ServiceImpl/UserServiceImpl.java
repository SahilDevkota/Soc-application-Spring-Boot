package com.example.SOCApplication.ServiceImpl;


import com.example.SOCApplication.DTO.RequestDTO.UserLoginDTO;
import com.example.SOCApplication.DTO.RequestDTO.UserRegDTO;
import com.example.SOCApplication.DTO.ResponseDTO.TokenResponseDTO;
import com.example.SOCApplication.Entity.RefreshToken;
import com.example.SOCApplication.Entity.User;
import com.example.SOCApplication.Enum.Roles;
import com.example.SOCApplication.Exception.LoginException;
import com.example.SOCApplication.Mapper.UserRegMapper;
import com.example.SOCApplication.Repository.RefreshTokenRepository;
import com.example.SOCApplication.Repository.UserRepository;
import com.example.SOCApplication.Service.UserService;
import com.example.SOCApplication.Util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    //Mapper converts DTO->Entity
    private final UserRegMapper userRegMapper;

    //Handles Refresh Token operations
    private final RefreshTokenRepository refreshTokenRepository;

    //Handles DB operations
    private final UserRepository userRepository;

    //Encodes password
    private final PasswordEncoder passwordEncoder;

    //Utility package to generate JWT tokens
    private final JWTUtil jwtUtil;

    private int attempt;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //--------REGISTER USER----------------
    @Override
    public ResponseEntity<String> registerUser(UserRegDTO userRegDTO) {


        //Checks if user with same userame exists or not
        if(userRepository.existsByUsername(userRegDTO.getUsername())){
            throw new RuntimeException("Username already exists. Try another one");
        }

        //Maps DTO->Entity
        User user = userRegMapper.regDTOtoUser(userRegDTO);

        //Make sure the password is encrypted before saving in the database
        user.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));

        //Setting the role to viewer as default
        user.setRole(Roles.VIEWER);
        //Saving inside the database
        userRepository.save(user);

        return ResponseEntity.ok("User added successfully");
    }

    //--------LOGIN USER----------------
    @Override
    public ResponseEntity<?> UserLogin(UserLoginDTO userLoginDTO) {


        String username = userLoginDTO.getUsername();



            //Checks if the user already exists in the database
            User user = userRepository.findByUsername(userLoginDTO.getUsername()).orElseThrow(()-> new LoginException("Username or password not found"));

            //Checks if the two hashed password matches
            if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {

                logger.info("User logged in successfully");
                user.setFailedLoginAttempt(0);

                //Create token response object
                TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();

                //Create Refresh Token object
                RefreshToken refreshToken = new RefreshToken();

                //Generating an Access Token
                tokenResponseDTO.setAccessToken(jwtUtil.GenerateAccessToken(user.getUsername()));

                //Generating a Refresh Token
                String generatedRefreshToken = jwtUtil.GenerateRefreshToken(user.getUsername());

                //Setting the refresh token to the DTO
                tokenResponseDTO.setRefreshToken(generatedRefreshToken);

                // Grabbing the expiration date of the token
                Date expiration = jwtUtil.getExpirationDateFromToken(generatedRefreshToken);

                //Setting up the Refresh Token object
                refreshToken.setToken(generatedRefreshToken);
                refreshToken.setUsername(user.getUsername());
                refreshToken.setExpirationDate(expiration);


                //Saving the Refresh token inside database
                refreshTokenRepository.save(refreshToken);

                ResponseCookie cookie = ResponseCookie.from("refreshtoken", generatedRefreshToken)
                        .httpOnly(true)
                        .secure(true)
                        .path("/")
                        .maxAge(Duration.ofDays(7))
                        .sameSite("Strict")
                        .build();
                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, cookie.toString())
                        .body(Map.of("AccessToken", tokenResponseDTO.getAccessToken()));
            } else {

                attempt = user.getFailedLoginAttempt();
                attempt += 1;
                user.setFailedLoginAttempt(attempt);
                userRepository.save(user);

                if (attempt <= 4) {
                    logger.info("Invalid Credentials");
                } else if (attempt >= 5 && attempt <= 7) {
                    logger.warn("Alert!! User is trying to log in multiple times. Send, the data to the user to analyze");
                } else if (attempt >= 8) {
                    logger.warn("call defender");
                }
                //If password is wrong, returns unauthorized response.

                throw new LoginException("Invalid username or password");
            }


    }
}
