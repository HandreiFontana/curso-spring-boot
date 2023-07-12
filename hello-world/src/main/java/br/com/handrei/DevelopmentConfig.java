package br.com.handrei;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class DevelopmentConfig {

    @Bean(name = "exec production")
    public CommandLineRunner exec() {
        return args -> {
          System.out.println("Running Development");
        };
    };
}
