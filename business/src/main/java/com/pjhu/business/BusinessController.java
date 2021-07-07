package com.pjhu.business;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business")
public class BusinessController {

    private final BusinessApplicationService businessApplicationService;

    @PostMapping("/buy")
    public ResponseEntity<Object> buy(@RequestBody BusinessCommand command) {
        Long storage = businessApplicationService.buy(command);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(storage);
    }

    @PostMapping("/buy-error")
    public ResponseEntity<Object> buyError(@RequestBody BusinessCommand command) {
        Long storage = businessApplicationService.buyError(command);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(storage);
    }
}
