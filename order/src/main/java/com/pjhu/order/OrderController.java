package com.pjhu.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    @PostMapping
    public ResponseEntity<Object> placeOrder(@RequestBody OrderPlaceCommand command) {
        Long orderId = orderApplicationService.placeOrder(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
