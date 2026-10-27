package com.example.SOCApplication.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SOCApplication.DTO.RequestDTO.UserLoginDTO;
import com.example.SOCApplication.ServiceImpl.UserServiceImpl;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private UserServiceImpl userService;

    private MockWebServer server;


    @Test
    void loginUsersShouldReturn() throws Exception{

        UserLoginDTO userLoginDTO = new UserLoginDTO("Sahil","Devkota");

        ResponseEntity<?> responseEntity = ResponseEntity.ok(Map.of("Access Token","fake-token"));


        doReturn(responseEntity)
                .when(userService)
                        .UserLogin(userLoginDTO);

        mockMvc.perform(
                post("/user/login")
                        .contentType("application/json")
                        .content("""
                                {
                                "username" : "Sahil",
                                "password": "Devkota"
                                }
                                """)
        ).andExpect(status().isOk());

    }

    @Test
    void PasswordMatchesTheEncodedPassword() throws Exception {

        String encodedPassword = passwordEncoder.encode("HelloWorld");

        UserLoginDTO loginDTO = new UserLoginDTO("Sahil","HelloWorld");

        passwordEncoder.matches(loginDTO.getPassword(),encodedPassword);

    }



}



