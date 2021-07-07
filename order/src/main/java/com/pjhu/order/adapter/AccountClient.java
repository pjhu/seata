package com.pjhu.order.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "account", url = "http://seataaccount:8080")
public interface AccountClient {

    @PostMapping("/accounts/decrease")
    ResponseEntity<Long> decreaseStorage(@RequestBody AccountDecreaseCommand command);
}
