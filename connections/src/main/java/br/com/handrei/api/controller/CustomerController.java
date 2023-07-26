package br.com.handrei.api.controller;

import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.repository.Customers;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private Customers repository;

    public CustomerController(Customers customers) {
        this.repository = customers;
    }

    @GetMapping("/")
    public List<Customer> search(Customer search) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(search, matcher);

        return repository.findAll(example);
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Integer id) {
      return repository
              .findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody @Valid Customer customer) {
        return repository.save(customer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Customer customer) {
        repository
                .findById(id)
                .map(customerResult -> {
                    customer.setId(customerResult.getId());
                    repository.save(customer);
                    return customer;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository
                .findById(id)
                .map(customerResult -> {
                    repository.delete(customerResult);
                    return customerResult;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

}
