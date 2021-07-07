package com.pjhu.business.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "order", url = "http://seataorder:8080")
public interface OrderClient {

    @PostMapping("/orders")
    ResponseEntity<Long> placeOrder(@RequestBody OrderPlaceCommand command);
}
