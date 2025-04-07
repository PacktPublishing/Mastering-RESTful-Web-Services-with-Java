package com.packt.ordermanagementapi.adapter.outbound.rest;

import com.packt.ordermanagementapi.adapter.exception.EntityNotFoundException;
import com.packt.ordermanagementapi.adapter.outbound.rest.dto.ProductOutputDto;
import com.packt.ordermanagementapi.usecase.ProductDetails;
import com.packt.ordermanagementapi.usecase.ProductsQueryUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

    private final ProductsApi productsApi;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsQueryUseCaseImpl.class);

    public ProductsQueryUseCaseImpl(ProductsApi productsApi) {
        this.productsApi = productsApi;
    }

    @Override
    public ProductDetails getProductById(String productId) {
        try {
            LOGGER.info("Getting product with id {}", productId);
            ProductOutputDto product = productsApi.getProductById(productId);
            return new ProductDetails(product.getSku(), product.getPrice());
        } catch (Exception ex) {
            LOGGER.error("Error getting product with id {}", productId, ex);
            throw new EntityNotFoundException("Product not found with id " + productId);
        }
    }
}
