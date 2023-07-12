package br.com.handrei.domain.repository;

import br.com.handrei.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Customers extends JpaRepository<Customer, Integer> {

    List<Customer> findByNameLike(String name);

    List<Customer> findByNameLikeOrIdOrderById(String name, Integer id);

    Customer findOneByName(String name);

    boolean existsByName(String name);
}
