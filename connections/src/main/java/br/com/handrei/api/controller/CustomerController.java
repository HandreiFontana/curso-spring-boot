package br.com.handrei.api.controller;

import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.repository.Customers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private Customers customers;

    public CustomerController(Customers customers) {
        this.customers = customers;
    }

    @GetMapping("/")
    public List<Customer> search(Customer search) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(search, matcher);

        return customers.findAll(example);
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Integer id) {
      return customers
              .findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer) {
        return customers.save(customer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Customer customer) {
        customers
                .findById(id)
                .map(customerResult -> {
                    customer.setId(customerResult.getId());
                    customers.save(customer);
                    return customer;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        customers
                .findById(id)
                .map(customerResult -> {
                    customers.delete(customerResult);
                    return customerResult;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

}
