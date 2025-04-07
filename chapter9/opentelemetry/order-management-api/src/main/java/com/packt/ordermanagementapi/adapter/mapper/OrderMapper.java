package com.packt.ordermanagementapi.adapter.mapper;

import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderRequestBodyDto;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.OrderResponseDto;
import com.packt.ordermanagementapi.domain.Order;
import com.packt.ordermanagementapi.usecase.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CustomerCustomMapper.class, componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderRequest orderRequestBody);

    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerDtoToCustomer")
    OrderRequest toOrderRequest(OrderRequestBodyDto orderRequestBody);

    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerToCustomerDto")
    OrderResponseDto toOrderResponse(Order order);
}
