package com.packt.ordermanagementapi.adapter.outbound.database;

import com.packt.ordermanagementapi.adapter.exception.EntityNotFoundException;
import com.packt.ordermanagementapi.adapter.mapper.OrderMapper;
import com.packt.ordermanagementapi.adapter.outbound.database.entity.OrderEntity;
import com.packt.ordermanagementapi.domain.Order;
import com.packt.ordermanagementapi.domain.Product;
import com.packt.ordermanagementapi.domain.StatusEnum;
import com.packt.ordermanagementapi.usecase.OrderRequest;
import com.packt.ordermanagementapi.usecase.OrdersCommandUseCase;
import com.packt.ordermanagementapi.usecase.ProductDetails;
import com.packt.ordermanagementapi.usecase.ProductsQueryUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrdersCommandUseCaseImpl implements OrdersCommandUseCase {

    private final OrdersRepository ordersRepository;
    private final OrderMapper orderMapper;
    private final ProductsQueryUseCase productsQueryUseCase;

    public OrdersCommandUseCaseImpl(OrdersRepository ordersRepository,
                                    OrderMapper orderMapper,
                                    ProductsQueryUseCase productsQueryUseCase) {
        this.ordersRepository = ordersRepository;
        this.orderMapper = orderMapper;
        this.productsQueryUseCase = productsQueryUseCase;
    }

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        updateProductPrice(orderRequest.products());
        Order order = orderMapper.toOrder(orderRequest);
        return ordersRepository.save(OrderEntity.fromOrder(order, null));
    }

    @Override
    public Order updateOrder(String orderId, OrderRequest orderRequest) {
        Optional<OrderEntity> optionalOrderEntity = ordersRepository.findById(orderId);
        if (optionalOrderEntity.isPresent()) {
            updateProductPrice(orderRequest.products());
            Order order = orderMapper.toOrder(orderRequest);
            return ordersRepository.save(OrderEntity.fromOrder(order, optionalOrderEntity.get()));
        }
        throw new EntityNotFoundException("Order not found with id " + orderId);
    }

    private void updateProductPrice(List<Product> products) {
        products.forEach(orderProduct -> {
            ProductDetails catalogueProduct = productsQueryUseCase.getProductById(orderProduct.getProductSKU());
            orderProduct.setPrice(catalogueProduct.price());
        });
    }

    @Override
    public Order updateOrderStatus(String orderId, StatusEnum status) {
        Optional<OrderEntity> optionalOrderEntity = ordersRepository.findById(orderId);
        if (optionalOrderEntity.isPresent()) {
            return ordersRepository.save(OrderEntity.updateOrderStatus(optionalOrderEntity.get(), status));
        }
        throw new EntityNotFoundException("Order not found with id " + orderId);
    }

    @Override
    public void deleteOrder(String orderId) {
        ordersRepository.deleteById(orderId);
    }
}
