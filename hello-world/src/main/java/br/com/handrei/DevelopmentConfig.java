package br.com.handrei;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner exec() {
        return args -> {
          System.out.println("Running Development");
        };
    };
}
