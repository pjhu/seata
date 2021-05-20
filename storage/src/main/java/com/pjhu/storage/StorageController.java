package com.pjhu.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/storages")
public class StorageController {

    private final StorageApplicationService storageApplicationService;

    @PostMapping("/decrease")
    public ResponseEntity<Object> decreaseStorage(@RequestBody StorageDecreaseCommand command) {
        Long storage = storageApplicationService.decreaseStorage(command);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(storage);
    }
}
