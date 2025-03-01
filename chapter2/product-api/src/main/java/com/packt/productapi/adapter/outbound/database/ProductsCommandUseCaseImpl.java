package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.exception.EntityNotFoundException;
import com.packt.productapi.adapter.outbound.database.entity.Product;
import com.packt.productapi.usecase.ProductsCommandUseCase;
import com.packt.productapi.usecase.dto.CreatedProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductsCommandUseCaseImpl implements ProductsCommandUseCase {

    private final ProductsRepository productsRepository;

    public ProductsCommandUseCaseImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public CreatedProduct createProduct(Product product) {
        final boolean exists = productsRepository.existsById(product.sku());
        return new CreatedProduct(productsRepository.save(product), !exists);
    }

    @Override
    public void deleteProduct(String productId) {
        productsRepository.deleteById(productId);
    }

    @Override
    public Product updateProductDescription(String productId, String description) {
        final var productEntity = productsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
        final var updatedProductEntity = new Product(productEntity.sku(),productEntity.name(),description, productEntity.price());
        return productsRepository.save(updatedProductEntity);
    }
}
