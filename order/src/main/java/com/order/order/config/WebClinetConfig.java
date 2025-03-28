package com.order.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClinetConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    @Bean
    public WebClient inventoryWebclient(){
        return WebClient.builder().baseUrl("http://localhost:8080/api/v1").build();
    }

    @Bean
    public WebClient productWebclient(){
        return WebClient.builder().baseUrl("http://localhost:8080/api/v1").build();
    }

}
