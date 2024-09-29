package com.packt.productapi.usecase;

import com.packt.productapi.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsQueryUseCase {
    Product getProductById(String productId);

    Page<? extends Product> getAllProducts(Pageable pageRequest);
}
