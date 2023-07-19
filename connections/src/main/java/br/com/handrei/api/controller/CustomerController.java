package br.com.handrei.api.controller;

import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.repository.Customers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private Customers customers;

    public CustomerController(Customers customers) {
        this.customers = customers;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable Integer id) {
      Optional<Customer> customer = customers.findById(id);

      if (customer.isPresent()) {
          return ResponseEntity.ok(customer.get());
      }

      return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity create(@RequestBody Customer customer) {
        Customer customerResponse = customers.save(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Customer customer) {
        return customers
                .findById(id)
                .map(customerResult -> {
                    customer.setId(customerResult.getId());
                    customers.save(customer);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        return customers
                .findById(id)
                .map(customerResult -> {
                    customers.delete(customerResult);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
