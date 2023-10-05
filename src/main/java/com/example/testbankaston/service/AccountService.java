package com.example.testbankaston.service;

import com.example.testbankaston.dto.AccountCreateDto;
import com.example.testbankaston.dto.AccountOperationDto;
import com.example.testbankaston.dto.AccountStatus;

import java.util.List;

public interface AccountService {
    List<AccountStatus> getAll();

    AccountCreateDto createAccount(AccountCreateDto newAccount);

    AccountStatus operationAccount(Long id, AccountOperationDto newOperation);
}
