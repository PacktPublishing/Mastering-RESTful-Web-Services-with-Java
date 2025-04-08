package com.packt.productapi.adapter.outbound.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUCT")
public record Product(
        @Column(name = "NAME")
        String name,

        @Id
        @Column(name = "CO_SKU")
        String sku,

        @Column(name = "DESCRIPTION")
        String description,

        @Column(name = "PRICE")
        BigDecimal price
){

}

