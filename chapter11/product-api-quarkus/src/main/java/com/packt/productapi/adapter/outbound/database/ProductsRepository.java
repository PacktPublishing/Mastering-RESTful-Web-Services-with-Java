package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.outbound.database.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductsRepository implements PanacheRepositoryBase<ProductEntity, String> {
}
