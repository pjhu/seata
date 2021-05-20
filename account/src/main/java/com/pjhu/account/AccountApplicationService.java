package com.pjhu.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountApplicationService {

    private final AccountRepository accountRepository;

    public Long decreaseAccount(AccountDecreaseCommand command) {
        Account account = accountRepository.findById(command.getUserId()).orElseThrow();
        account.decrease(command.getOrderAmount());
        Account savedAccount = accountRepository.save(account);
        return savedAccount.getId();
    }
}
