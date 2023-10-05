package com.example.testbankaston.controller;

import com.example.testbankaston.dto.AccountCreateDto;
import com.example.testbankaston.dto.AccountOperationDto;
import com.example.testbankaston.dto.AccountStatus;
import com.example.testbankaston.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final AccountService service;

    @GetMapping
    public List<AccountStatus> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountCreateDto createAccount(@Valid @RequestBody AccountCreateDto newAccount) {
        return service.createAccount(newAccount);
    }

    @PatchMapping(value = "/{id}")
    public AccountStatus operationAccount(@PathVariable Long id, @Valid @RequestBody AccountOperationDto newOperation) {
        return service.operationAccount(id, newOperation);
    }
}
