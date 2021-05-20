package com.pjhu.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private final AccountApplicationService accountApplicationService;

    @PostMapping("/decrease")
    public ResponseEntity<Object> decreaseAccount(@RequestBody AccountDecreaseCommand command) {
        Long orderId = accountApplicationService.decreaseAccount(command);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderId);
    }
}
