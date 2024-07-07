package com.packt.productapi.usecase;

import com.packt.productapi.domain.Product;

public record CreatedProduct(Product product, boolean isNewProduct) {
}
