package com.packt.ordermanagementapi.usecase;

import com.packt.ordermanagementapi.domain.Order;
import com.packt.ordermanagementapi.domain.StatusEnum;

public interface OrdersCommandUseCase {

    Order createOrder(OrderRequest order);

    Order updateOrder(String orderId, OrderRequest orderRequest);

    Order updateOrderStatus(String orderId, StatusEnum status);

    void deleteOrder(String orderId);
}
