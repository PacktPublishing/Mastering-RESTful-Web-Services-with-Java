package com.packt.productapi;

import com.packt.productapi.domain.Product;
import com.packt.productapi.domain.ProductPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.concurrent.CompletableFuture;

public interface ProductsQueryUseCase {
    Product getProductById(String productId);
    CompletableFuture<Product> getProductByIdAsync(String productId);
    ProductPhoto getProductPhotoById(String productId);

    Page<? extends Product> getAllProducts(Pageable pageRequest);
}
