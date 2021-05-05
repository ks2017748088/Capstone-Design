package com.nayoung.app.service;


import com.nayoung.app.controller.AccountForm;
import com.nayoung.app.domain.Account;
import com.nayoung.app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long createUser(AccountForm form) {
        Account account = form.toEntity();
        accountRepository.save(account);
        return account.getId();
    }
}