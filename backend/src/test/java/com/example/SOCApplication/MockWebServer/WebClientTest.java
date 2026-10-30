package com.example.SOCApplication.MockWebServer;

import com.example.SOCApplication.DTO.RequestDTO.LogbackDTO;
import com.example.SOCApplication.ServiceImpl.AnalyzerServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class WebClientTest {

    private LogbackDTO logbackDTO;

    private AnalyzerServiceImpl analyzerService;

    private MockWebServer webServer;

    @BeforeEach
    void setUp() throws IOException{

        webServer = new MockWebServer();
        webServer.start();

        WebClient webClient = WebClient.builder()
                .baseUrl(webServer.url("/").toString())
                .build();

        analyzerService = new AnalyzerServiceImpl(webClient);
    }

    @Test
    void testTheWebClient() throws InterruptedException{

        logbackDTO = new LogbackDTO();
        logbackDTO.setUsername("Sahil");
        logbackDTO.setEvent("User is still trying to log in into their account after numerous attemps");
        logbackDTO.setTimestamp(LocalDateTime.now());
        logbackDTO.setFailedAttempts(10);

        List<LogbackDTO> logbackDTOList = new ArrayList<LogbackDTO>(List.of(logbackDTO));


        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"body\" : \" A lot of attempt has been made for this account. Need to call Defender Agent\"}")
                .addHeader("Content-Type: application/json"));

        String result = analyzerService.callAnalyzer(logbackDTOList);

        assertEquals("{\"body\" : \" A lot of attempt has been made for this account. Need to call Defender Agent\"}",result);

    }

    @Test
    void testThatWebClientReturnsBadRequest() throws InterruptedException{


        logbackDTO = new LogbackDTO();
        logbackDTO.setUsername("Sahil");
        logbackDTO.setEvent("User is still trying to log in into their account after numerous attemps");
        logbackDTO.setTimestamp(LocalDateTime.now());
        logbackDTO.setFailedAttempts(10);

        List<LogbackDTO> logbackDTOList = new ArrayList<LogbackDTO>(List.of(logbackDTO));


        webServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody("{\"Error\" : \" Invalid request Data \"}")
                .addHeader("Content-Type: application/json"));


        WebClientResponseException exception = assertThrows(
                WebClientResponseException.class,()-> analyzerService.callAnalyzer(logbackDTOList)
        );

        assertEquals(400,exception.getStatusCode().value());


    }


    @AfterEach
    void tearDown() throws IOException{
        webServer.shutdown();
    }


}
