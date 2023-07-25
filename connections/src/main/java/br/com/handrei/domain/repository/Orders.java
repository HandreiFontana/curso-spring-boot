package br.com.handrei.domain.repository;

import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Orders extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);

    @Query("select o from Order o left join fetch o.orderItems where o.id = :id")
    Optional<Order> findByIdFetchOrderItems(@Param("id") Integer id);
}
