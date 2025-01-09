package com.packt.ordermanagementapi.usecase;

import com.packt.ordermanagementapi.domain.Order;

import java.util.List;

public interface OrdersQueryUseCase {

    List<? extends Order> getAllOrders();

    Order getOrder(String orderId);

}
