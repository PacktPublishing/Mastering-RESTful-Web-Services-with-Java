package com.packt.ordermanagementapi.adapter.inbound.rest;

import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderRequestBodyDto;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderResponseDto;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderStatusDto;
import com.packt.ordermanagementapi.adapter.mapper.OrderMapper;
import com.packt.ordermanagementapi.domain.StatusEnum;
import com.packt.ordermanagementapi.usecase.OrdersCommandUseCase;
import com.packt.ordermanagementapi.usecase.OrdersQueryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderManagementApiController implements OrderManagementApi {

    private final OrdersCommandUseCase ordersCommandUseCase;
    private final OrdersQueryUseCase ordersQueryUseCase;
    private final OrderMapper orderMapper;

    public OrderManagementApiController(OrdersCommandUseCase ordersCommandUseCase,
                                        OrdersQueryUseCase ordersQueryUseCase,
                                        OrderMapper orderMapper) {
        this.ordersCommandUseCase = ordersCommandUseCase;
        this.ordersQueryUseCase = ordersQueryUseCase;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<List<OrderResponseDto>> ordersGet() {
        final var orders = ordersQueryUseCase.getAllOrders()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(orders);
    }

    @Override
    public ResponseEntity<OrderResponseDto> ordersOrderIdGet(String orderId) {
        final var order = ordersQueryUseCase.getOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderMapper.toOrderResponse(order));
    }

    @Override
    public ResponseEntity<OrderResponseDto> ordersPost(OrderRequestBodyDto orderRequestBody) {
        final var order = ordersCommandUseCase.createOrder(orderMapper.toOrderRequest(orderRequestBody));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toOrderResponse(order));
    }

    @Override
    public ResponseEntity<OrderResponseDto> ordersOrderIdStatusPatch(String orderId, OrderStatusDto orderStatus) {
        final var order = ordersCommandUseCase.updateOrderStatus(orderId, StatusEnum.valueOf(orderStatus.getStatus().name()));
        return ResponseEntity.status(HttpStatus.OK).body(orderMapper.toOrderResponse(order));
    }

    @Override
    public ResponseEntity<OrderResponseDto> ordersOrderIdPut(String orderId, OrderRequestBodyDto orderRequestBody) {
        var orderRequest = orderMapper.toOrderRequest(orderRequestBody);
        final var order = ordersCommandUseCase.updateOrder(orderId, orderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(orderMapper.toOrderResponse(order));
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdDelete(String orderId) {
        ordersCommandUseCase.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
