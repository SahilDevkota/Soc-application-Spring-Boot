package com.example.SOCApplication.ServiceImpl;


import com.example.SOCApplication.DTO.RequestDTO.LogbackDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class AnalyzerServiceImpl {

    private final WebClient webClient;

    public void callAnalyzer(List<LogbackDTO> logbackDTO){

        String haha = webClient.post()
                .uri("http://127.0.0.1:8000/callAnalyzer")
                .bodyValue(logbackDTO)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(haha);
    }

}
