package com.packt.ordermanagementapi.adapter.outbound.database.entity;

import com.packt.ordermanagementapi.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUCT")
public class ProductEntity extends Product {

    @Id
    @Column(name = "SKU")
    @Override
    public String getProductSKU() {
        return super.getProductSKU();
    }

    @Column(name = "QTY")
    @Override
    public Integer getQuantity() {
        return super.getQuantity();
    }

    @Column(name = "PRICE")
    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    public static ProductEntity fromProduct(Product product) {
        var productEntity = new ProductEntity();
        productEntity.setProductSKU(product.getProductSKU());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }
}
