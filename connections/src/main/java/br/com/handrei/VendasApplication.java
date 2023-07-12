package br.com.handrei;

import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.repository.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Customers customers) {
        return args -> {
            Customer customer = new Customer("Handrei");
            customers.create(customer);

            Customer customer2 = new Customer("Milena");
            customers.create(customer2);

            List<Customer> allCustomers = customers.list();
            allCustomers.forEach(System.out::println);

            allCustomers.forEach(c -> {
                c.setName(c.getName() + " updated");
                customers.update(c);
            });

            allCustomers = customers.list();
            allCustomers.forEach(System.out::println);

            customers.list("ile").forEach(System.out::println);

            allCustomers.forEach(c -> {
                customers.delete(c);
                System.out.println(c + " deletado");
            });

            allCustomers = customers.list();

            if (allCustomers.isEmpty()) {
                System.out.println("Nenhum cliente encontrado");
            } else {
                allCustomers.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
