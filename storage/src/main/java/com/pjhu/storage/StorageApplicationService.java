package com.pjhu.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StorageApplicationService {

    private final StorageRepository storageRepository;

    @Transactional
    public Long decreaseStorage(StorageDecreaseCommand command) {
        Storage storage = storageRepository.findById(command.getProductId()).orElseThrow();
        storage.decrease(command.getCount());
        Storage savedStorage = storageRepository.save(storage);
        return savedStorage.getId();
    }
}
