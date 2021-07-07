package com.pjhu.order;

import com.pjhu.order.adapter.AccountClient;
import com.pjhu.order.adapter.AccountDecreaseCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {

    private final AccountClient accountClient;
    private final OrderRepository orderRepository;

    public Long placeOrder(OrderPlaceCommand command) {
        Integer orderAmount = command.getProductCount() * 2;

        AccountDecreaseCommand accountDecreaseCommand =
                new AccountDecreaseCommand(command.getUserId(), orderAmount);
        accountClient.decreaseStorage(accountDecreaseCommand);

        Order order = new Order(command.getUserId(), command.getProductId(),
                command.getProductCount(), orderAmount);
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }
}
