package com.packt.productapi.usecase;


import com.packt.productapi.adapter.outbound.database.entity.Product;
import com.packt.productapi.usecase.dto.CreatedProduct;

public interface ProductsCommandUseCase {
    CreatedProduct createProduct(Product product);

    void deleteProduct(String productId);

    Product updateProductDescription(String productId, String description);
}
