package com.packt.productapi.usecase;

import com.packt.productapi.domain.Product;
import com.packt.productapi.domain.ProductPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsQueryUseCase {
    Product getProductById(String productId);
    ProductPhoto getProductPhotoById(String productId);

    Page<? extends Product> getAllProducts(Pageable pageRequest);
}
