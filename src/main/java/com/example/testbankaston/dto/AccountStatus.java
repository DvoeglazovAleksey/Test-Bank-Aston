package com.example.testbankaston.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountStatus {
    private String name;
    private BigDecimal deposit;
}
