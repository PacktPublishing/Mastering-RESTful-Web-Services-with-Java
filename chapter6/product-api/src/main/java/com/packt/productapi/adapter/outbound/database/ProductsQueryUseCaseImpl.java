package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.exception.EntityNotFoundException;
import com.packt.productapi.domain.Product;
import com.packt.productapi.domain.ProductPhoto;
import com.packt.productapi.usecase.ProductsQueryUseCase;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

    private final ProductsRepository productsRepository;
    private final PhotoProductsRepository photoProductsRepository;
    private final TimeLimiter timeLimiter;

    public ProductsQueryUseCaseImpl(ProductsRepository productsRepository,
                                    PhotoProductsRepository photoProductsRepository, TimeLimiterRegistry timeLimiterRegistry) {
        this.productsRepository = productsRepository;
        this.photoProductsRepository = photoProductsRepository;
        this.timeLimiter = timeLimiterRegistry.timeLimiter("productServiceGetById");
    }

    @Override
    public Product getProductById(String productId) {
        return productsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    @Override
    public CompletableFuture<Product> getProductByIdAsync(String productId) {
        return timeLimiter.executeCompletionStage(
                        Executors.newSingleThreadScheduledExecutor(),
                        () -> CompletableFuture.supplyAsync(() -> getProductById(productId)))
                .toCompletableFuture();
    }

    @Override
    public ProductPhoto getProductPhotoById(String productId) {
        return photoProductsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    @Override
    public Page<? extends Product> getAllProducts(Pageable pageRequest) {
        return productsRepository.findAll(pageRequest);
    }
}
