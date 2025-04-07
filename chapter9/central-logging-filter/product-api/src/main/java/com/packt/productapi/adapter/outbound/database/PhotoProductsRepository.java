package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.outbound.database.entity.ProductPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoProductsRepository extends JpaRepository<ProductPhotoEntity, String> {
}
