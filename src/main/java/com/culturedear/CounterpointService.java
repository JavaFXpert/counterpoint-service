package com.culturedear;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CounterpointService {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CounterpointService.class).web(true).run(args);
    }
}


