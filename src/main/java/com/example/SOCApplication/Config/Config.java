package com.example.SOCApplication.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {


    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

}
