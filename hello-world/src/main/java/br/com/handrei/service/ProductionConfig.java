package br.com.handrei.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class ProductionConfig {

    @Bean
    public CommandLineRunner exec() {
        return args -> {
            System.out.println("Running Production");
        };
    };
}
