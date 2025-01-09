package com.packt.ordermanagementapi.adapter.outbound.rest;

import com.packt.ordermanagementapi.adapter.outbound.rest.dto.ProductOutputDto;
import com.packt.ordermanagementapi.usecase.ProductDetails;
import com.packt.ordermanagementapi.usecase.ProductsQueryUseCase;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.http.HttpConnectTimeoutException;
import java.util.concurrent.TimeoutException;

@Service
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

    private final ProductsApi productsApi;

    public ProductsQueryUseCaseImpl(ProductsApi productsApi) {
        this.productsApi = productsApi;
    }

    @Retryable(value = {
        SocketTimeoutException.class,
        TimeoutException.class,
        HttpConnectTimeoutException.class,
        ConnectException.class,
        ResourceAccessException.class
    },
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2))
    public ProductDetails getProductById(String productId) {
        ProductOutputDto product = productsApi.getProductById(productId);
        return new ProductDetails(product.getSku(), product.getPrice());
    }

    @Recover
    public ProductDetails recoverGetProductResource(ResourceAccessException e, String productId) {
        throw new ResourceAccessException(
            "Service temporarily unavailable - All retries failed for product id: [%s] due to timeout"
                .formatted(productId)
        );
    }
}
