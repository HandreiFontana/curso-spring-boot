package br.com.handrei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Value("${application.name}")
    private String applicationNameByApplicationProperties;

    @Autowired
    @Qualifier("applicationNameByConfiguration")
    private String applicationNameByConfiguration;

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationNameByApplicationProperties + " - " +applicationNameByConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}