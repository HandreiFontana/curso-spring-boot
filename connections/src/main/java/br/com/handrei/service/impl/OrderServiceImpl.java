package br.com.handrei.service.impl;

import br.com.handrei.api.dto.OrderDTO;
import br.com.handrei.api.dto.OrderItemDTO;
import br.com.handrei.domain.entity.Customer;
import br.com.handrei.domain.entity.Order;
import br.com.handrei.domain.entity.OrderItem;
import br.com.handrei.domain.entity.Product;
import br.com.handrei.domain.repository.Customers;
import br.com.handrei.domain.repository.OrderItems;
import br.com.handrei.domain.repository.Orders;
import br.com.handrei.domain.repository.Products;
import br.com.handrei.exception.UseCaseException;
import br.com.handrei.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final Orders repository;
    private final Customers customersRepository;
    private final Products productsRepository;
    private final OrderItems orderItemsRepository;

    @Override
    @Transactional
    public Order create(OrderDTO dto) {
        Integer customerId = dto.getCustomer();
        Customer customer = customersRepository
                .findById(customerId)
                .orElseThrow(() -> new UseCaseException("Customer Id invalid"));

        Order order = new Order();
        order.setOrderValue(dto.getOrderValue());
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);

        Set<OrderItem> orderItems = convertItems(order, dto.getItems());

        repository.save(order);
        orderItemsRepository.saveAll(orderItems);

        return order;
    }

    private Set<OrderItem> convertItems(Order order, List<OrderItemDTO> items) {
        if (items.isEmpty()) {
            throw new UseCaseException("Order items not found");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer productId = dto.getProduct();
                    Product product = productsRepository
                            .findById(productId)
                            .orElseThrow(() -> new UseCaseException("Product Id invalid: " + productId));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(dto.getQuantity());
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    return orderItem;
                })
                .collect(Collectors.toSet());
    }
}
