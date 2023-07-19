package br.com.handrei;

import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.repository.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Customers customers) {
        return args -> {
            Customer c = new Customer(null, "Handrei");
            customers.save(c);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
