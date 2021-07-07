package com.pjhu.business.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "storage", url = "http://seatastorage:8080")
public interface StorageClient {

    @PostMapping("/storages/decrease")
    ResponseEntity<Long> decreaseStorage(@RequestBody StorageDecreaseCommand command);
}
