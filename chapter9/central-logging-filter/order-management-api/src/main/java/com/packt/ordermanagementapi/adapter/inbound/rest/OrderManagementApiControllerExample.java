//package com.packt.ordermanagementapi.adapter.inbound.rest;
//
//import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderRequestBodyDto;
//import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderResponseDto;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//public class OrderManagementApiControllerExample implements OrderManagementApi {
//    @Override
//    public ResponseEntity<List<OrderResponseDto>> ordersGet() {
//        //Add your own concrete implementation
//        return OrderManagementApi.super.ordersGet();
//    }
//
//    @Override
//    public ResponseEntity<Void> ordersOrderIdDelete(String orderId) {
//        //Add your own concrete implementation
//        return OrderManagementApi.super.ordersOrderIdDelete(orderId);
//    }
//
//    @Override
//    public ResponseEntity<OrderResponseDto> ordersOrderIdGet(String orderId) {
//        //Add your own concrete implementation
//        return OrderManagementApi.super.ordersOrderIdGet(orderId);
//    }
//
//    @Override
//    public ResponseEntity<OrderResponseDto> ordersOrderIdPut(String orderId, OrderRequestBodyDto orderRequestBodyDto) {
//        //Add your own concrete implementation
//        return OrderManagementApi.super.ordersOrderIdPut(orderId, orderRequestBodyDto);
//    }
//
////    @Override
//    public ResponseEntity<OrderResponseDto> ordersOrderIdStatusPatch(String orderId, String body) {
//        //Add your own concrete implementation
//        return OrderManagementApi.super.ordersOrderIdStatusPatch(orderId, body);
//    }
//
//    @Override
//    public ResponseEntity<OrderResponseDto> ordersPost(OrderRequestBodyDto orderRequestBodyDto) {
//        //Add your own concrete implementation
//        return OrderManagementApi.super.ordersPost(orderRequestBodyDto);
//    }
//}
