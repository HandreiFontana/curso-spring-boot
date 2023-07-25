package br.com.handrei.api.controller;

import br.com.handrei.api.dto.OrderDTO;
import br.com.handrei.domain.entity.Order;
import br.com.handrei.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody OrderDTO dto) {
        Order order = service.create(dto);
        return order.getId();
    }
}
