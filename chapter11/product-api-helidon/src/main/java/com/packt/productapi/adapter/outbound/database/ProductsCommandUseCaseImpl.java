package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.exception.EntityNotFoundException;
import com.packt.productapi.adapter.outbound.database.entity.ProductEntity;
import com.packt.productapi.domain.Product;
import com.packt.productapi.usecase.dto.CreatedProduct;
import com.packt.productapi.usecase.ProductsCommandUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
@Transactional
public class ProductsCommandUseCaseImpl implements ProductsCommandUseCase {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CreatedProduct createProduct(Product product) {
        var existingProductEntity = em.find(ProductEntity.class, product.getSku());
        if (existingProductEntity == null) {
            ProductEntity newProductEntity = new ProductEntity();
            newProductEntity.setSku(product.getSku());
            updateEntity(product, newProductEntity);
            em.persist(newProductEntity);
        } else {
            updateEntity(product, existingProductEntity);
            em.flush();
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
        Optional.ofNullable(em.find(ProductEntity.class, productId)).ifPresent(em::remove);
    }

    @Override
    public Product updateProductDescription(String productId, String description) {
        final var productEntity = Optional.ofNullable(em.find(ProductEntity.class, productId))
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
        productEntity.setDescription(description);
        em.persist(productEntity);
        return productEntity.toProduct();
    }
}
