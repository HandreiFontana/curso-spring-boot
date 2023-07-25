package br.com.handrei.api.controller;

import br.com.handrei.api.dto.InfoOrderDTO;
import br.com.handrei.api.dto.InfoOrderItemDTO;
import br.com.handrei.api.dto.OrderDTO;
import br.com.handrei.domain.entity.Order;
import br.com.handrei.domain.entity.OrderItem;
import br.com.handrei.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    @GetMapping("/{id}")
    public InfoOrderDTO get(@PathVariable Integer id) {
        return service
                .get(id)
                .map(order -> convert(order))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order not found"));
    }

    private InfoOrderDTO convert(Order order) {
        return InfoOrderDTO
                .builder()
                .code(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getCustomer().getCpf())
                .customerName(order.getCustomer().getName())
                .orderValue(order.getOrderValue())
                .items(convert(order.getOrderItems()))
                .build();
    }

    private List<InfoOrderItemDTO> convert(List<OrderItem> items) {
        if(CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items
                .stream()
                .map(item -> InfoOrderItemDTO
                        .builder()
                        .description(item.getProduct().getDescription())
                        .unitValue(item.getProduct().getUnitValue())
                        .quantity(item.getQuantity())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
