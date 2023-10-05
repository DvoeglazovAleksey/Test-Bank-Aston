package com.example.testbankaston.mapper;

import com.example.testbankaston.dto.AccountCreateDto;
import com.example.testbankaston.dto.AccountStatus;
import com.example.testbankaston.model.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public Account toAccount(AccountCreateDto newAccount) {
        Account account = new Account();
        account.setName(newAccount.getName());
        account.setPin(newAccount.getPin());
        return account;
    }

    public AccountCreateDto toAccountCreateDto(Account account) {
        return new AccountCreateDto(account.getName(), account.getPin());
    }

    public AccountStatus toAccountStatus(Account account) {
        return new AccountStatus(account.getName(), account.getDeposit());
    }
}
