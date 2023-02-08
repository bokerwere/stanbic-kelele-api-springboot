package com.boker.StanbicKeleleApi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class StanbicApiConfigurations {
    @Value("${stanbic.clientId}")
    private String clientId;
    @Value("${stanbic.clientSecret}")
    private String clientSecret;
    @Bean
    ObjectMapper objectMapper(){
        return  new ObjectMapper();
    }
}
