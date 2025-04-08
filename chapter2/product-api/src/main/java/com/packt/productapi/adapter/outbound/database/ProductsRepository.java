package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.outbound.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductsRepository extends JpaRepository<Product, String> {
    @Modifying
    @Query("UPDATE Product p SET p.description = :description WHERE p.sku = :productId")
    void updateDescriptionById(@Param("productId") String productId, @Param("description") String description);
}
