package com.packt.ordermanagementapi.adapter.outbound.database.entity;

import com.packt.ordermanagementapi.domain.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "TB_ORDER")
public class OrderEntity extends Order {

    @Override
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return super.getId();
    }

    @Override
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    public CustomerEntity getCustomer() {
        return super.getCustomer();
    }

    public void setCustomer(CustomerEntity customer) {
        super.setCustomer(customer);
    }

    @Override
    @OneToMany(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "ORDER_ID")
    public List<ProductEntity> getProducts() {
        return (List<ProductEntity>) super.getProducts();
    }

    @Override
    public void setProducts(List<? extends Product> products) {
        super.setProducts(products);
    }

    @Override
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    public StatusEnum getStatus() {
        return super.getStatus();
    }

    @Override
    @Column(name = "CREATED_DATE", nullable = false)
    public OffsetDateTime getOrderCreatedDate() {
        return super.getOrderCreatedDate();
    }

    @Override
    @Column(name = "UPDATED_DATE")
    public OffsetDateTime getOrderUpdatedDate() {
        return super.getOrderUpdatedDate();
    }

    @PrePersist
    protected void onCreate() {
        if (this.getStatus() == null) {
            this.setStatus(StatusEnum.PENDING);
        }
    }

    public static OrderEntity updateOrderStatus(OrderEntity orderEntity, StatusEnum status) {
        orderEntity.setStatus(status);
        orderEntity.setOrderUpdatedDate(OffsetDateTime.now());
        return orderEntity;
    }

    public static OrderEntity fromOrder(Order order, OrderEntity orderEntity) {

        CustomerEntity customerEntity = switch (order.getCustomer()) {
            case PersonCustomer personCustomer -> PersonCustomerEntity.fromCustomer(personCustomer);
            case CompanyCustomer companyCustomer -> CompanyCustomerEntity.fromCustomer(companyCustomer);
            case null, default -> null;
        };

        if (Objects.isNull(orderEntity)) {
            orderEntity = new OrderEntity();
            orderEntity.setOrderCreatedDate(OffsetDateTime.now());
        } else {
            orderEntity.setOrderUpdatedDate(OffsetDateTime.now());
        }

        orderEntity.setCustomer(customerEntity);
        orderEntity.setProducts(order.getProducts()
                .stream()
                .map(ProductEntity::fromProduct)
                .collect(Collectors.toList()));
        return orderEntity;
    }

    @Override
    public BigDecimal getTotalAmount() {
        return getProducts()
                .stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}