package com.packt.productapi.adapter.outbound.database.entity;

import com.packt.productapi.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUCT")
public class ProductEntity extends Product {

    @Id
    @Column(name = "CO_SKU")
    @Override
    public String getSku() {
        return super.getSku();
    }

    @Column(name = "NAME")
    @Override
    public String getName() {
        return super.getName();
    }

    @Column(name = "DESCRIPTION")
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Column(name = "PRICE")
    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }


    public static ProductEntity fromProduct(Product product) {
        var productEntity = new ProductEntity();
        productEntity.setSku(product.getSku());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }
}

