package com.packt.ordermanagementapi.adapter.outbound.rest;

import com.packt.ordermanagementapi.adapter.exception.EntityNotFoundException;
import com.packt.ordermanagementapi.adapter.outbound.rest.dto.ProductOutputDto;
import com.packt.ordermanagementapi.configuration.ProductsApiBeanPostProcessor;
import com.packt.ordermanagementapi.configuration.RequestLoggingFilter;
import com.packt.ordermanagementapi.usecase.ProductDetails;
import com.packt.ordermanagementapi.usecase.ProductsQueryUseCase;
import org.apache.coyote.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

    Logger logger = LoggerFactory.getLogger(ProductsQueryUseCaseImpl.class);

    private final ProductsApi productsApi;
    private final ProductsApiBeanPostProcessor beanPostProcessor;

    public ProductsQueryUseCaseImpl(ProductsApi productsApi, ProductsApiBeanPostProcessor beanPostProcessor) {
        this.productsApi = productsApi;
        this.beanPostProcessor = beanPostProcessor;
    }

    @Override
    public ProductDetails getProductById(String productId) {
        try {
            beanPostProcessor.postProcessBeforeInitialization(productsApi, "productsApi");
            ProductOutputDto product = productsApi.getProductById(productId);
            return new ProductDetails(product.getSku(), product.getPrice());
        } catch (Exception ex) {
            logger.error("Error getting product with id {}", productId, ex);
            throw new EntityNotFoundException("Product not found with id " + productId);
        }
    }
}
