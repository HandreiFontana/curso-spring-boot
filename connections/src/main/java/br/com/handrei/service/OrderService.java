package br.com.handrei.service;

import br.com.handrei.api.dto.OrderDTO;
import br.com.handrei.domain.entity.Order;

import java.util.Optional;

public interface OrderService {

    Order create(OrderDTO dto);

    Optional<Order> get(Integer id);
}
