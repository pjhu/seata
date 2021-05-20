package com.pjhu.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageApplicationService {

    private final StorageRepository storageRepository;

    public Long decreaseStorage(StorageDecreaseCommand command) {
        Storage storage = storageRepository.findById(command.getProductId()).orElseThrow();
        storage.decrease(command.getCount());
        Storage savedStorage = storageRepository.save(storage);
        return savedStorage.getId();
    }
}
