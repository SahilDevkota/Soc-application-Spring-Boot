package com.example.SOCApplication.MockWebServer;


import com.example.SOCApplication.Repository.UserRepository;
import com.example.SOCApplication.ServiceImpl.UserServiceImpl;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
public class WebClientTest {


    private UserServiceImpl userService;
    private MockWebServer webServer;

    @MockitoBean
    private UserRepository userRepository;

    @BeforeEach
    void setup() throws IOException{
        webServer = new MockWebServer();
        webServer.start();

        WebClient webClient = WebClient.
                builder().baseUrl(webServer.url("/").toString()).build();


    }

}
