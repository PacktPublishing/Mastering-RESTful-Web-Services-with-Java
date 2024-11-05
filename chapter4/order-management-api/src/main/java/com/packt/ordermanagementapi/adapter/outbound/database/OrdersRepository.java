package com.packt.ordermanagementapi.adapter.outbound.database;

import com.packt.ordermanagementapi.adapter.outbound.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrderEntity, String> {

}
