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

    public String callAnalyzer(List<LogbackDTO> logbackDTO){

        return webClient.post()
                .uri("/callAnalyzer")
                .bodyValue(logbackDTO)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
