package br.com.handrei.domain.repository;

import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Orders extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);
}
