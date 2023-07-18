package br.com.handrei.domain.repository;

import br.com.handrei.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Customers extends JpaRepository<Customer, Integer> {

    List<Customer> findByNameLike(String name);

    List<Customer> findByNameLikeOrIdOrderById(String name, Integer id);

    Customer findOneByName(String name);

    @Query(value = "select c from Customer c where c.name like :name")
    List<Customer> encontrarPorNome( @Param("name") String name);

    @Query("delete from Customer c where c.name = :name")
    @Modifying
    void deleteByName(String name);

    boolean existsByName(String name);

    @Query("select c from Customer c left join fetch c.orders where c.id = :id")
    Customer findCustomerFetchOrders(@Param("id") Integer id);
}
