package com.packt.productapi.usecase.dto;

import com.packt.productapi.adapter.outbound.database.entity.Product;

public record CreatedProduct(Product product, boolean isNewProduct) {
}
