package br.com.handrei.service.impl;

import br.com.handrei.domain.repository.Orders;
import br.com.handrei.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private Orders repository;

    public OrderServiceImpl(Orders repository) {
        this.repository = repository;
    }
}
