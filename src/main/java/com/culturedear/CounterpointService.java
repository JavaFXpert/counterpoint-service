package com.culturedear;


import com.culturedear.counterpoint.CounterpointProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan("com.culturedear.*")
public class CounterpointService {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CounterpointService.class).web(true).run(args);
    }

    @Bean(name = "configurationProperties")
    public static CounterpointProperties configurationProperties(CounterpointProperties configurationProperties) {
        CounterpointProperties.counterpointProperties = configurationProperties;
        return configurationProperties;
    }
}


