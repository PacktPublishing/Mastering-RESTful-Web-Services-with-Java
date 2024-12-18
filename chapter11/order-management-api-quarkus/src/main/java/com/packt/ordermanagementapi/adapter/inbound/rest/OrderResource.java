package com.packt.ordermanagementapi.adapter.inbound.rest;

import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderRequestBodyDto;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderResponseDto;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderStatusDto;

import java.util.List;

public class OrderResource implements OrdersApi {
    @Override
    public OrderResponseDto ordersPost(OrderRequestBodyDto orderRequestBodyDto) {
        return null;
    }

    @Override
    public List<OrderResponseDto> ordersGet() {
        return List.of(new OrderResponseDto().id("1"));
    }

    @Override
    public void ordersOrderIdDelete(String orderId) {

    }

    @Override
    public OrderResponseDto ordersOrderIdGet(String orderId) {
        return null;
    }

    @Override
    public OrderResponseDto ordersOrderIdPut(String orderId, OrderRequestBodyDto orderRequestBodyDto) {
        return null;
    }

    @Override
    public OrderResponseDto ordersOrderIdStatusPatch(String orderId, OrderStatusDto orderStatusDto) {
        return null;
    }

}
