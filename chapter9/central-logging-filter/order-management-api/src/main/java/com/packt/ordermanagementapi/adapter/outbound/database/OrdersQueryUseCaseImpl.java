package com.packt.ordermanagementapi.adapter.outbound.database;

import com.packt.ordermanagementapi.adapter.exception.EntityNotFoundException;
import com.packt.ordermanagementapi.domain.Order;
import com.packt.ordermanagementapi.usecase.OrdersQueryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersQueryUseCaseImpl implements OrdersQueryUseCase {

    private final OrdersRepository ordersRepository;

    public OrdersQueryUseCaseImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<? extends Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Order getOrder(String orderId) {
        return ordersRepository.findById(orderId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Order not found with id " + orderId)
                );
    }
}
