package com.pjhu.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {

    private final OrderRepository orderRepository;

    public Long placeOrder(OrderPlaceCommand command) {
        Integer orderAmount = command.getProductCount();
        Order order = new Order(command.getUserId(), command.getProductId(), command.getProductCount(), orderAmount);
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }
}
