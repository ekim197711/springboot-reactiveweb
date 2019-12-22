package com.example.springbootreactive.space.station;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NavigationClientConfiguration {

    @Bean(name = "navigation-web-client")
    public WebClient navWebClient() {
        WebClient webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .build();
        return webClient;
    }

}
