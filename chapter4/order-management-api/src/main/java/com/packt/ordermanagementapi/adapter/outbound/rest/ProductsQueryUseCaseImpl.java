package com.packt.ordermanagementapi.adapter.outbound.rest;

import com.packt.ordermanagementapi.adapter.outbound.rest.dto.ProductOutputDto;
import com.packt.ordermanagementapi.usecase.ProductDetails;
import com.packt.ordermanagementapi.usecase.ProductsQueryUseCase;
import org.springframework.stereotype.Service;

@Service
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

    private final ProductsApi productsApi;

    public ProductsQueryUseCaseImpl(ProductsApi productsApi) {
        this.productsApi = productsApi;
    }

    @Override
    public ProductDetails getProductById(String productId) {
        ProductOutputDto product = productsApi.getProductById(productId);
        return new ProductDetails(product.getSku(), product.getPrice());
    }
}
