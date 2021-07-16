package com.pjhu.business;

import com.pjhu.business.adapter.OrderClient;
import com.pjhu.business.adapter.OrderPlaceCommand;
import com.pjhu.business.adapter.StorageClient;
import com.pjhu.business.adapter.StorageDecreaseCommand;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessApplicationService {

    private final OrderClient orderClient;
    private final StorageClient storageClient;

    @GlobalTransactional(timeoutMills = 300000, name = "gts-seata-buy")
    public Long buy(BusinessCommand command) {
        StorageDecreaseCommand storageDecreaseCommand =
                new StorageDecreaseCommand(command.getProductId(), command.getCount());
        ResponseEntity<Long> storageDecreaseResponse = storageClient.decreaseStorage(storageDecreaseCommand);
        log.info("storageDecreaseResponse: {}", storageDecreaseResponse);

        // OrderPlaceCommand orderPlaceCommand =
        //         new OrderPlaceCommand(command.getUserId(), command.getProductId(), command.getCount());
        // ResponseEntity<Long> placeOrderResponse = orderClient.placeOrder(orderPlaceCommand);
        // log.info("placeOrderResponse: {}", placeOrderResponse);

        return storageDecreaseResponse.getBody();
    }

    @GlobalTransactional(timeoutMills = 300000, name = "gts-seata-buy")
    public Long buyRollBack(BusinessCommand command) {
        StorageDecreaseCommand storageDecreaseCommand =
                new StorageDecreaseCommand(command.getProductId(), command.getCount());
        ResponseEntity<Long> storageDecreaseResponse = storageClient.decreaseStorage(storageDecreaseCommand);
        log.info("storageDecreaseResponse: {}", storageDecreaseResponse);

        // OrderPlaceCommand orderPlaceCommand =
        //         new OrderPlaceCommand(command.getUserId(), command.getProductId(), command.getCount());
        // ResponseEntity<Long> placeOrderResponse = orderClient.placeOrder(orderPlaceCommand);
        // log.info("placeOrderResponse: {}", placeOrderResponse);
        throw new RuntimeException("分布式事务回滚");
    }
}
