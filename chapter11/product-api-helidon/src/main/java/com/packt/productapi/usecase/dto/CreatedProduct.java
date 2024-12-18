package com.packt.productapi.usecase.dto;

import com.packt.productapi.domain.Product;

public record CreatedProduct(Product product, boolean isNewProduct) {
}
