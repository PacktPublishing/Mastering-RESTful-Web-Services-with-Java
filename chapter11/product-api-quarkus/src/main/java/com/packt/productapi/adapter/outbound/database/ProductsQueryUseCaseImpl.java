package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.exception.EntityNotFoundException;
import com.packt.productapi.adapter.outbound.database.entity.ProductEntity;
import com.packt.productapi.domain.Product;
import com.packt.productapi.usecase.ProductsQueryUseCase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

    private final ProductsRepository productsRepository;

    public ProductsQueryUseCaseImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Product getProductById(String productId) {
        return productsRepository.findByIdOptional(productId)
                .map(ProductEntity::toProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    @Override
    public List<Product> getAllProducts() {
        return productsRepository.listAll().stream().map(ProductEntity::toProduct).toList();
    }
}
