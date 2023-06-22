package br.com.handrei;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name = "applicationNameByConfiguration")
    public String applicationName() {
        return "Vendas Application by Configuration";
    }
}
