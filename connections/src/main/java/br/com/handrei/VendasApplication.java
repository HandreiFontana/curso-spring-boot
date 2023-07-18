package br.com.handrei;

import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.entity.Order;
import br.com.handrei.domain.repository.Customers;
import br.com.handrei.domain.repository.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Customers customers,
            @Autowired Orders orders
    ) {
        return args -> {
            Customer customer = new Customer("Handrei");
            customers.save(customer);

            Customer customer2 = new Customer("Milena");
            customers.save(customer2);

            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderDate(LocalDate.now());
            order.setOrderValue(BigDecimal.valueOf(100));
            orders.save(order);
            order.setOrderValue(BigDecimal.valueOf(200));

            Customer customerFound = customers.findCustomerFetchOrders(customer.getId());
            System.out.println(customerFound);
            System.out.println(customerFound.getOrders());

            orders.findByCustomer(customer).forEach(System.out::println);
//            List<Customer> allCustomers = customers.findAll();
//            allCustomers.forEach(System.out::println);
//
//            boolean exists = customers.existsByName("Milena");
//            System.out.println(exists);
//
//            allCustomers.forEach(c -> {
//                c.setName(c.getName() + " updated");
//                customers.save(c);
//            });
//
//            allCustomers = customers.findAll();
//            allCustomers.forEach(System.out::println);
//
//            customers.findByNameLike("%ile%").forEach(System.out::println);
//            customers.encontrarPorNome("%ile%").forEach(System.out::println);
//
//            allCustomers.forEach(c -> {
//                customers.delete(c);
//                System.out.println(c + " deletado");
//            });
//
//            allCustomers = customers.findAll();
//
//            if (allCustomers.isEmpty()) {
//                System.out.println("Nenhum cliente encontrado");
//            } else {
//                allCustomers.forEach(System.out::println);
//            }


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
