package br.com.handrei;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Production
public class ProductionConfig {

    @Bean(name = "execProduction")
    public CommandLineRunner exec() {
        return args -> {
            System.out.println("Running Production");
        };
    };
}
