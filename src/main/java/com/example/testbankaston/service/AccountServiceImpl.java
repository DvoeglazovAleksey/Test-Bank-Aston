package com.example.testbankaston.service;

import com.example.testbankaston.dto.AccountCreateDto;
import com.example.testbankaston.dto.AccountOperationDto;
import com.example.testbankaston.dto.AccountStatus;
import com.example.testbankaston.exceptions.NotFoundException;
import com.example.testbankaston.mapper.Mapper;
import com.example.testbankaston.model.Account;
import com.example.testbankaston.repository.Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final Repository repository;

    @Override
    public List<AccountStatus> getAll() {
        return repository.findAll().stream()
                .map(Mapper::toAccountStatus)
                .collect(Collectors.toList());
    }

    @Override
    public AccountCreateDto createAccount(AccountCreateDto newAccount) {
        Account result = Mapper.toAccount(newAccount);
        result.setDeposit(BigDecimal.valueOf(0));
        return Mapper.toAccountCreateDto(repository.save(result));
    }

    @Override
    public AccountStatus operationAccount(Long id, AccountOperationDto newOperation) {
        Account account = checkAccount(id);
        switch (newOperation.getOperation()) {
            case DEPOSIT:
                account.setDeposit(account.getDeposit().add(newOperation.getDeposit()));
                account = repository.save(account);
                break;
            case TRANSFER:
                subtractForDeposit(account, newOperation);
                Account accountTransfer = checkAccount(newOperation.getIdAccountForTransfer());
                BigDecimal depositTransfer = accountTransfer.getDeposit().add(newOperation.getDeposit());
                accountTransfer.setDeposit(depositTransfer);
                repository.save(accountTransfer);
                break;
            case WITHDRAW:
                subtractForDeposit(account, newOperation);
                break;
            default:
                log.warn("Unknown operation: UNSUPPORTED_OPERATION");
                throw new NotFoundException("Unknown operation: UNSUPPORTED_OPERATION");
        }
        return Mapper.toAccountStatus(account);
    }

    private Account checkAccount(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Account not found or unavailable."));
    }

    private void subtractForDeposit(Account account, AccountOperationDto newOperation) {
        checkPinCode(account.getPin(), newOperation.getPin());
        BigDecimal deposit = account.getDeposit().subtract(newOperation.getDeposit());
        account.setDeposit(deposit);
        repository.save(account);
    }

    private void checkPinCode(Integer accountPin, Integer pin) {
        String stringPin = pin.toString();
        if (pin < 0 || pin > 9999 || stringPin.length() != 4) {
            log.warn("The pin code is not correct");
            throw new NotFoundException("The pin code is not correct");
        }
        if (!Objects.equals(accountPin, pin)) {
            log.warn("Pin not for account");
            throw new NotFoundException("Pin not for account");
        }
    }
}
