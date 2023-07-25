package br.com.handrei.service;

import br.com.handrei.api.dto.OrderDTO;
import br.com.handrei.domain.entity.Order;

public interface OrderService {

    Order create(OrderDTO dto);
}
