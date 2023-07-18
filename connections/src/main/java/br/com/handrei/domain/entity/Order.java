package br.com.handrei.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_value", precision = 20, scale = 2)
    private BigDecimal orderValue;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public Set<OrderItem> getOrderItems() { return orderItems; }

    public void setOrderItems(Set<OrderItem> orderItems) { this.orderItems = orderItems; }

    public LocalDate getOrderDate() { return orderDate; }

    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public BigDecimal getOrderValue() { return orderValue; }

    public void setOrderValue(BigDecimal orderValue) { this.orderValue = orderValue; }

    public Customer getCustomerId() {
        return customer;
    }

    public void setCustomerId(Customer customerId) {
        this.customer = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", orderValue=" + orderValue +
                '}';
    }
}
