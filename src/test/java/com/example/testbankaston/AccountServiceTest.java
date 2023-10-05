package com.example.testbankaston;

import com.example.testbankaston.dto.AccountCreateDto;
import com.example.testbankaston.dto.AccountOperationDto;
import com.example.testbankaston.dto.AccountStatus;
import com.example.testbankaston.enums.Operation;
import com.example.testbankaston.model.Account;
import com.example.testbankaston.repository.Repository;
import com.example.testbankaston.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @InjectMocks
    AccountServiceImpl service;

    @Mock
    Repository repository;

    private AccountCreateDto accountCreateDto;
    private Account account;
    private AccountOperationDto newOperation;

    @BeforeEach
    void setup() {
        accountCreateDto = new AccountCreateDto("Alex", 1234);

        account = new Account();
        account.setName("Alex");
        account.setPin(1234);
        account.setDeposit(BigDecimal.valueOf(0));
        account.setId(1L);

        newOperation = new AccountOperationDto(1L, Operation.DEPOSIT, 1234, BigDecimal.valueOf(100));
    }

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(List.of(account));

        List<AccountStatus> result = service.getAll();

        assertEquals(result.get(0).getName(), account.getName());
    }

    @Test
    void createAccount() {
        when(repository.save(any())).thenReturn(account);

        AccountCreateDto result = service.createAccount(accountCreateDto);

        assertEquals(result.getPin(), account.getPin());
    }

    @Test
    void operationAccount() {
        Long id = account.getId();
        when(repository.save(any())).thenReturn(account);
        when(repository.findById(id)).thenReturn(Optional.ofNullable(account));

        AccountStatus result = service.operationAccount(id, newOperation);

        assertEquals(result.getDeposit(), newOperation.getDeposit());
    }
}