package com.packt.productapi.usecase;

import com.packt.productapi.adapter.outbound.database.entity.Product;

import java.util.List;

public interface ProductsQueryUseCase {
    Product getProductById(String productId);

    List<? extends Product> getAllProducts();
}
