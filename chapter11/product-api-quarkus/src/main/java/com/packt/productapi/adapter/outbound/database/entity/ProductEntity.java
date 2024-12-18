package com.packt.productapi.adapter.outbound.database.entity;

import com.packt.productapi.domain.Product;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUCT")
public class ProductEntity extends PanacheEntityBase {

    private String sku;
    private String name;
    private String description;
    private BigDecimal price;

    @Id
    @Column(name = "CO_SKU")
    public String getSku() {
        return sku;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product toProduct() {
        Product product = new Product();
        product.setSku(sku);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}

