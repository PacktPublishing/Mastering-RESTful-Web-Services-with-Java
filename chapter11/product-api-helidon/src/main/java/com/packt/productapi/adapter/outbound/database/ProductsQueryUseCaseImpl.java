package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.exception.EntityNotFoundException;
import com.packt.productapi.adapter.outbound.database.entity.ProductEntity;
import com.packt.productapi.domain.Product;
import com.packt.productapi.usecase.ProductsQueryUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product getProductById(String productId) {
        return Optional.ofNullable(em.find(ProductEntity.class, productId))
                .map(ProductEntity::toProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    @Override
    public List<Product> getAllProducts() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
        Root<ProductEntity> root = criteriaQuery.from(ProductEntity.class);
        criteriaQuery.select(root);
        Query query = em.createQuery(criteriaQuery);
        List<?> resultList = query.getResultList();
        return resultList.stream()
                .map(entity -> ((ProductEntity) entity).toProduct())
                .toList();
    }
}
