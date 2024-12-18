package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.exception.EntityNotFoundException;
import com.packt.productapi.adapter.outbound.database.entity.ProductEntity;
import com.packt.productapi.domain.Product;
import com.packt.productapi.usecase.dto.CreatedProduct;
import com.packt.productapi.usecase.ProductsCommandUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ProductsCommandUseCaseImpl implements ProductsCommandUseCase {

    private final ProductsRepository productsRepository;

    public ProductsCommandUseCaseImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public CreatedProduct createProduct(Product product) {
        var existingProductEntity = productsRepository.findById(product.getSku());
        if (existingProductEntity == null) {
            ProductEntity newProductEntity = new ProductEntity();
            newProductEntity.setSku(product.getSku());
            updateEntity(product, newProductEntity);
            productsRepository.persist(newProductEntity);
        } else {
            updateEntity(product, existingProductEntity);
            productsRepository.flush();
        }
        return new CreatedProduct(product, existingProductEntity == null);
    }

    private void updateEntity(Product product, ProductEntity productEntity) {
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
    }

    @Override
    public void deleteProduct(String productId) {
        productsRepository.deleteById(productId);
    }

    @Override
    public Product updateProductDescription(String productId, String description) {
        final var productEntity = productsRepository.findByIdOptional(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
        productEntity.setDescription(description);
        return productEntity.toProduct();
    }
}
