package com.packt.ordermanagementapi.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class Order {

    private Long id;

    private Customer customer;

    private List<? extends Product> products;

    private BigDecimal totalAmount;

    private OffsetDateTime orderCreatedDate;

    private OffsetDateTime orderUpdatedDate;

    private StatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public <C extends Customer> C getCustomer() {
        return (C) customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<? extends Product> getProducts() {
        return products;
    }

    public void setProducts(List<? extends Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OffsetDateTime getOrderCreatedDate() {
        return orderCreatedDate;
    }

    public void setOrderCreatedDate(OffsetDateTime orderCreatedDate) {
        this.orderCreatedDate = orderCreatedDate;
    }

    public OffsetDateTime getOrderUpdatedDate() {
        return orderUpdatedDate;
    }

    public void setOrderUpdatedDate(OffsetDateTime orderUpdatedDate) {
        this.orderUpdatedDate = orderUpdatedDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
