package br.com.handrei.domain.repository;

import br.com.handrei.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Orders extends JpaRepository<Order, Integer> {
}
