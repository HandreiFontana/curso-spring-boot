package br.com.handrei.service;

import br.com.handrei.model.Customer;
import br.com.handrei.repository.CustomersRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {

    private CustomersRepository repository;

    public void CustomersService(CustomersRepository repository) {
        this.repository = repository;
    }

    public void create(Customer customer) {
        validate(customer);

        this.repository.create(customer);
    }

    public void validate(Customer customer) {

    }
}
